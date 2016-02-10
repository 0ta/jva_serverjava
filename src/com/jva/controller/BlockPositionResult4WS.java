package com.jva.controller;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Servlet implementation class BlockPositionResult
 */
@ServerEndpoint("/BPR")
@WebServlet("/BlockPositionResult4WS")
public class BlockPositionResult4WS extends JVAServlet {
	private static final long serialVersionUID = 1L;
	private static final Queue<Session> sessions = new ConcurrentLinkedQueue<Session>();
	private final Object writeLock = new Object();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlockPositionResult4WS() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.broadcast();
	}
	
	@OnOpen
	public void connect(Session session) {
		System.out.println("onOpen:" + session);
		sessions.add(session);
	}		

	@OnClose
	public void onClose(Session session) {
		System.out.println("onClose:" + session);
		sessions.remove(session);
	}
	
	private void broadcast() {
		synchronized (writeLock) {
			String jsonstr = this.getResultJson();
			for (Session session: sessions) {
				//System.out.println("Successfully push[" + jsonstr + "]");
				session.getAsyncRemote().sendText(jsonstr);
			}			
		}
	}
}
