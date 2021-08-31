package fr.uracraft.uramod.client;

import fr.uracraft.uramod.common.CommonProxy;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRender()
    {
        System.out.println("méthode côté client");
    }
}
