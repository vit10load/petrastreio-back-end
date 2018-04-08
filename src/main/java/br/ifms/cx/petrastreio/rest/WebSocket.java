/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.cx.petrastreio.rest;

import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Gustavo
 */
@ServerEndpoint(value = "/coords")
public class WebSocket {
    public static List<Session> clientes;

    public WebSocket() {
        clientes = new ArrayList<Session>();
    }   
    
    @OnOpen
    public void onOpen(Session session) {    
        System.out.println("Connected ... " + session.getId());
        clientes.add(session);
    }
}
