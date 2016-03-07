package model;

public class Critter {
  

    public GridLocation gridl;
    int health;
    
    public Critter(GridLocation gridl, int health) {
        super();
        this.gridl = gridl;
        this.health=health;
    }
   
    public Critter(Critter c){
        this.gridl=c.gridl;
        this.health=c.health;
    }

}
