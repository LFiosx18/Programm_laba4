package server;

import serialPack.Request;
import serialPack.Response;
import serverLib.CollectionMng;
import serverLib.commands.Commander;

public class Processing {
    private CollectionMng collectionMng;
    private Commander commander;

    public Processing(CollectionMng collectionMng) {
        this.collectionMng = collectionMng;
        this.commander = new Commander(collectionMng);
    }

    public Response process(Request request) {
        String mes = commander.define(request.getCommand(), request.getArgument(), request.getProduct());
        return new Response(mes);
    }
}
