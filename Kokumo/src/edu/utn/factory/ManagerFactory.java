package edu.utn.factory;

import edu.utn.manager.ServiceManager;
import edu.utn.manager.GameManager;
import edu.utn.manager.PlayerManager;
import edu.utn.manager.RuleManager;

public class ManagerFactory {

    public GameManager createManager(){

        return new GameManager(createCommunicationManger(),createRulesManager(),createPlayerManger());
    }
    public ServiceManager createCommunicationManger(){

        return new ServiceManager();
    }

    public RuleManager createRulesManager(){

        return new RuleManager();
    }

    public PlayerManager createPlayerManger(){

        return new PlayerManager();
    }
}
