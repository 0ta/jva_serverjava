package com.jva.controller;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.jva.pojo.PassPOJO;
import com.jva.pojo.ResultPOJO;
import com.jva.pojo.RootPOJO;
import com.jva.pojo.SetterPositionPOJO;

public abstract class JVAServlet extends HttpServlet {
    protected Connection con = null;
    protected StringBuffer sql = null;
	private String currentScore = "";
	private static Logger logger = LogManager.getLogger("com.jva.jsonhistory");
    
	protected String getResultJson() {
		RootPOJO root = new RootPOJO();
		try {
			if (con == null) {	
				//Create connection
				Driver d = (Driver) Class.forName(
						"com.microsoft.sqlserver.jdbc.SQLServerDriver")
						.newInstance();
				String connUrl = "jdbc:sqlserver://localhost;user=jvajava;password=jvajava";
				con = d.connect(connUrl, new Properties());				
			}
			
			// Get data from score table
			//Create SQL
			//sql = new StringBuffer("select GAME_ID, \"Set\", Rally_Seq, _VAR1, P_num_R, proba_rr_TARGET_P1, proba_rr_TARGET_P2, proba_rr_TARGET_P3, proba_rr_TARGET_P4, proba_rr_TARGET_P5, proba_rr_TARGET_P6 from Score_tbl order by ");
			sql = new StringBuffer("select GAME_ID, \"Set\", Rally_Seq, _VAR1, P_num_R, proba_rr_TARGET_P2, proba_rr_TARGET_P3, proba_rr_TARGET_P4, proba_rr_TARGET_P5, proba_rr_TARGET_P6 from Score_tbl order by ");
			sql.append("_VAR1, P_num_R");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());

			List<PassPOJO> passes = new ArrayList<PassPOJO>();
			List<SetterPositionPOJO> setterpositions = new ArrayList<SetterPositionPOJO>();
			ResultPOJO result = new ResultPOJO();

			while (rs.next()) {
				// GameID
				result.setGameId(rs.getString("GAME_ID"));
				// Set
				result.setSet(String.valueOf(Math.round(rs.getFloat("Set"))));
				// Rally number
				result.setRallyNumber(rs.getInt("Rally_Seq"));

				// Pass(Target per reception player)
				PassPOJO pass = new PassPOJO();
				pass.setReceptionPlayer(String.valueOf(Math.round(rs.getFloat("P_num_R"))));
				//pass.setTargetPercent_p1(String.valueOf(Math.round(rs.getFloat("proba_rr_TARGET_P1") * 100)));
				pass.setTargetPercent_p2(String.valueOf(Math.round(rs.getFloat("proba_rr_TARGET_P2") * 100)));
				pass.setTargetPercent_p3(String.valueOf(Math.round(rs.getFloat("proba_rr_TARGET_P3") * 100)));
				pass.setTargetPercent_p4(String.valueOf(Math.round(rs.getFloat("proba_rr_TARGET_P4") * 100)));
				pass.setTargetPercent_p5(String.valueOf(Math.round(rs.getFloat("proba_rr_TARGET_P5") * 100)));
				pass.setTargetPercent_p6(String.valueOf(Math.round(rs.getFloat("proba_rr_TARGET_P6") * 100)));
				passes.add(pass);

				if ("6".equals(pass.getReceptionPlayer())) {
					// Setter Position
					SetterPositionPOJO setterposition = new SetterPositionPOJO();
					setterposition.setPosition(String.valueOf(Math.round(rs.getFloat("_VAR1"))));
					setterposition.setPasses(passes);
					setterpositions.add(setterposition);
					passes = new ArrayList<PassPOJO>();
				}
			}
			result.setSetterPositions(setterpositions);
			
			// Get data from next table
			rs.close();
			stmt.close();
			sql = new StringBuffer("select Rotation_A, H_Point, A_Point, aP1, aP2, aP3, aP4, aP5, aP6 from Next_Rally where ");
			sql.append("GAME_ID = '" + result.getGameId() + "' and \"Set\" = " + result.getSet() + " and Rally_Seq = " + result.getRallyNumber());
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			
			if (rs.next()) {
				result.setScore(String.valueOf(Math.round(rs.getFloat("H_Point"))) + " - " + String.valueOf(Math.round(rs.getFloat("A_Point"))));		
				result.setCurrentRotation(rs.getString("Rotation_A"));
				result.setUninum_p1(rs.getString("aP1"));
				result.setUninum_p2(rs.getString("aP2"));
				result.setUninum_p3(rs.getString("aP3"));
				result.setUninum_p4(rs.getString("aP4"));			
				result.setUninum_p5(rs.getString("aP5"));
				result.setUninum_p6(rs.getString("aP6"));				
			}
			
			// Get data from correct answer table
			rs.close();
			stmt.close();
			sql = new StringBuffer("select a.GAME_ID as GAME_ID, a.\"Set\" as \"Set\", a.Rally_Seq as Rally_Seq, a.P_num_Pred as P_num_Pred, a.proba_rr_TARGET as proba_rr_TARGET, a.P_num_Answer as P_num_Answer, a.P_num_R as P_num_R, a.Answer_KBN as Answer_KBN, b.Rotation_A as Rotation_A ");
			sql.append("from Correct_Answer a ");
			sql.append("left outer join Next_Rally b ");
			sql.append("on a.GAME_ID = b.GAME_ID and a.\"Set\" = b.\"Set\" and a.Rally_Seq = b.Rally_Seq ");			
			sql.append("where a.GAME_ID = '" + result.getGameId() + "' ");
			sql.append("order by a.Rally_Seq");
			System.out.println(sql);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			
			int totalNum = 0;
			int totalCorrectNum = 0;
			
			int numPerRotation1 = 0;
			int correctNumPerRotation1 = 0;
			int numPerRotation2 = 0;
			int correctNumPerRotation2 = 0;
			int numPerRotation3 = 0;
			int correctNumPerRotation3 = 0;
			int numPerRotation4 = 0;
			int correctNumPerRotation4 = 0;
			int numPerRotation5 = 0;
			int correctNumPerRotation5 = 0;
			int numPerRotation6 = 0;
			int correctNumPerRotation6 = 0;
			
			while (rs.next()) {
				// Set actual done atack number 
				int rotationInt = Math.round(rs.getFloat("Rotation_A"));
				int receptionInt = Math.round(rs.getFloat("P_num_R"));
				int answerInt = Math.round(rs.getFloat("P_num_Answer"));
				if (receptionInt > 1) {
					PassPOJO pp = result.getSetterPositions().get(rotationInt - 1).getPasses().get(receptionInt - 2);
					int sumInt = 0;
					switch(answerInt) {
					case 2:
						sumInt = Integer.valueOf(pp.getActualAtacknum_p2());
						pp.setActualAtacknum_p2(String.valueOf(++sumInt));
						break;
					case 3:
						sumInt = Integer.valueOf(pp.getActualAtacknum_p3());
						pp.setActualAtacknum_p3(String.valueOf(++sumInt));
						break;
					case 4:
						sumInt = Integer.valueOf(pp.getActualAtacknum_p4());
						pp.setActualAtacknum_p4(String.valueOf(++sumInt));
						break;
					case 5:
						sumInt = Integer.valueOf(pp.getActualAtacknum_p5());
						pp.setActualAtacknum_p5(String.valueOf(++sumInt));
						break;
					case 6:
						sumInt = Integer.valueOf(pp.getActualAtacknum_p6());
						pp.setActualAtacknum_p6(String.valueOf(++sumInt));
						break;
					}
				}
				result.sumSetterPositionsPassesActualAtackNum();
				
				// Set hit ratio 
				boolean isHit = false;
				String ansKbnStr = rs.getString("Answer_KBN");
				if ("1".equals(ansKbnStr)) {
					isHit = true;
					totalCorrectNum++;
				}
				totalNum++;
				// Set hit ratio per Rotaion
				switch(rotationInt) {
				case 1:
					numPerRotation1++;
					if (isHit) {
						correctNumPerRotation1++;
					}
					break;
				case 2:
					numPerRotation2++;
					if (isHit) {
						correctNumPerRotation2++;
					}
					break;
				case 3:
					numPerRotation3++;
					if (isHit) {
						correctNumPerRotation3++;
					}
					break;
				case 4:
					numPerRotation4++;
					if (isHit) {
						correctNumPerRotation4++;
					}
					break;
				case 5:
					numPerRotation5++;
					if (isHit) {
						correctNumPerRotation5++;
					}
					break;
				case 6:
					numPerRotation6++;
					if (isHit) {
						correctNumPerRotation6++;
					}
					break;
				}
				// Set hit ratio per Set
				int set = Math.round(rs.getFloat("Set"));
				int i = 0;
				int j = 0;
				switch(set) {
				case 1:
					if (isHit) {
						i = result.getAnswerHitratio_correctNum_1();
						result.setAnswerHitratio_correctNum_1(++i);
					}
					j = result.getAnswerHitratio_num_1();
					result.setAnswerHitratio_num_1(++j);
					break;
				case 2:
					if (isHit) {
						i = result.getAnswerHitratio_correctNum_2();
						result.setAnswerHitratio_correctNum_2(++i);
					}
					j = result.getAnswerHitratio_num_2();
					result.setAnswerHitratio_num_2(++j);
					break;
				case 3:
					if (isHit) {
						i = result.getAnswerHitratio_correctNum_3();
						result.setAnswerHitratio_correctNum_3(++i);
					}
					j = result.getAnswerHitratio_num_3();
					result.setAnswerHitratio_num_3(++j);
					break;
				case 4:
					if (isHit) {
						i = result.getAnswerHitratio_correctNum_4();
						result.setAnswerHitratio_correctNum_4(++i);
					}
					j = result.getAnswerHitratio_num_4();
					result.setAnswerHitratio_num_4(++j);
					break;
				case 5:
					if (isHit) {
						i = result.getAnswerHitratio_correctNum_5();
						result.setAnswerHitratio_correctNum_5(++i);
					}
					j = result.getAnswerHitratio_num_5();
					result.setAnswerHitratio_num_5(++j);
					break;
				}
			}
			if (numPerRotation1 != 0) {
				result.getSetterPositions().get(0).setAnswerHitratio_correctNum(correctNumPerRotation1);
				result.getSetterPositions().get(0).setAnswerHitratio_num(numPerRotation1);
			}
			if (numPerRotation2 != 0) {
				result.getSetterPositions().get(1).setAnswerHitratio_correctNum(correctNumPerRotation2);
				result.getSetterPositions().get(1).setAnswerHitratio_num(numPerRotation2);
			}
			if (numPerRotation3 != 0) {
				result.getSetterPositions().get(2).setAnswerHitratio_correctNum(correctNumPerRotation3);
				result.getSetterPositions().get(2).setAnswerHitratio_num(numPerRotation3);
			}
			if (numPerRotation4 != 0) {
				result.getSetterPositions().get(3).setAnswerHitratio_correctNum(correctNumPerRotation4);
				result.getSetterPositions().get(3).setAnswerHitratio_num(numPerRotation4);
			}
			if (numPerRotation5 != 0) {
				result.getSetterPositions().get(4).setAnswerHitratio_correctNum(correctNumPerRotation5);
				result.getSetterPositions().get(4).setAnswerHitratio_num(numPerRotation5);
			}
			if (numPerRotation6 != 0) {
				result.getSetterPositions().get(5).setAnswerHitratio_correctNum(correctNumPerRotation6);
				result.getSetterPositions().get(5).setAnswerHitratio_num(numPerRotation6);
			}
			
			if (totalNum != 0) {
				result.setAnswerHitratio_correctNum_total(totalCorrectNum);
				result.setAnswerHitratio_num_total(totalNum);
			}

			// for test
			result.setRrate_p2("61.3");
			result.setRrate_p3("16.3");
			result.setRrate_p4("14.6");
			result.setRrate_p5("32.3");
			result.setRrate_p6("24.3");

			// Calculate hit ratio percentage
			result.culculateAllPercentage();
			
			// Root
			root.setResult(result);
			rs.close();
			stmt.close();
		} catch (Exception ex) {
			System.err.println(ex.getClass().getName());
			System.err.println(ex.getMessage());
		}
			
		Gson gson = new Gson();
		String resultJson = gson.toJson(root);
		//if (!currentScore.equals(root.getResult().getScore())) {
			logger.info(new Date().getTime() + "|" + resultJson);
			//currentScore = root.getResult().getScore();
		//}
		return resultJson;
	}
}
