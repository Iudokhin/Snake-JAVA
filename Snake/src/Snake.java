import java.util.ArrayList;

public class Snake {
    private SnakeDirection direction;
    private boolean isAlive;
    private ArrayList<SnakeSection> sections;

    public Snake (int x, int y) {
        sections = new ArrayList<>();
        sections.add(new SnakeSection(x,y));
        isAlive=true;
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public ArrayList<SnakeSection> getSections() {
        return sections;
    }

    public int getX() {
        return sections.get(0).getX();
    }

    public int getY(){
        return sections.get(0).getY();
    }

    public void move () {
        if (direction==SnakeDirection.UP)
            move(0,-1);
        else if (direction==SnakeDirection.RIGHT)
            move(1,0);
        else if (direction==SnakeDirection.DOWN)
            move (0,1);
        else if(direction==SnakeDirection.LEFT)
            move(-1,0);
    }

    public void move (int dx, int dy) {
        SnakeSection head = sections.get(0);
        head=new SnakeSection(head.getX() + dx, head.getY() + dy);

        checkBorders(head);
        if(!isAlive) return;

        checkBody(head);
        if(!isAlive) return;

        Mouse mouse = Room.game.getMouse();
        if(head.getX() == mouse.getX() && head.getY()== mouse.getY()) {
            sections.add(0, head);
            Room.game.eatMouse();
        }else {
            sections.add(0, head);
            sections.remove(sections.size() -1 );
        }
    }
    private void checkBorders (SnakeSection head) {
        if ((head.getX() < 0 || head.getX() >= Room.game.getWidth()) || head.getY() <0 || head.getY() >= Room.game.getHeight()) {
            isAlive=false;
        }
    }

    private void checkBody (SnakeSection head) {
        if (sections.contains(head)) {
            isAlive=false;
        }
    }
}
