package br.ifms.cx.petrastreio.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ifms.cx.petrastreio.dao.CoordenadaDAO;
import br.ifms.cx.petrastreio.domain.Coordenada;
import br.ifms.cx.petrastreio.domain.Response;
import com.google.gson.Gson;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import org.glassfish.tyrus.server.Server;

@Path("/coords")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class CoordenadasResource {

    @Autowired
    private CoordenadaDAO db;

    private static Server ws;

    private static ScheduledExecutorService executor;

    public CoordenadasResource() {
        if (ws == null) {
            ws = new Server("localhost", 8025, "/websockets", WebSocket.class);
        }
    }

    @GET
    public Coordenada getUltimaCoordenada() {
        Coordenada c = db.getUltimaCoordenada();
        System.out.println(c.getLatitude());
        System.out.println(c.getLongitude());

        return db.getUltimaCoordenada();
    }

    @POST
    @Transactional
    public Response setCoordenada(Coordenada coordenada) {
        System.out.println(coordenada.getLatitude());
        System.out.println(coordenada.getLongitude());
        try {
            db.persistirCoordenada(coordenada);
            for (Session s : WebSocket.clientes) {
                if (s.isOpen()) {
                    Gson g = new Gson();
                    String json = g.toJson(coordenada);
                    s.getAsyncRemote().sendText(json);
                }
            }
            return Response.Ok("Coordenada incluida com sucesso");
        } catch (Exception ex) {
            return Response.Error(ex.getMessage());
        }
    }

    @GET
    @Path("/list/{qtde}")
    public List<Coordenada> listarCoordenadas(@PathParam("qtde") Integer qtde) {
        return db.listarCoordenadas(qtde);
    }
}
