package model.ninja.element.genkai;


import model.ninja.Attack;
import model.ninja.element.Chakra;

import java.util.List;

public abstract class KekkeiGenkai implements Attack {

    private List<Chakra> chakraList;

    public List<Chakra> getChakraList() {
        return chakraList;
    }

    public void setChakraList(List<Chakra> chakraList) {
        this.chakraList = chakraList;
    }
}
