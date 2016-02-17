public class Cell {
    private boolean live;
    private boolean isEdge = false;
    private int numOfLiveNeighbor;
    private int numOfLiveNeighborBuffer;
    public Cell[] neighbor;
    private boolean edge;

    public Cell() {
        live = false;
    }

    public boolean isLive() {
        return live;
    }

    public void revive() {
        live = true;
    }

    public void die() {
        live = false;
    }

    public void init(Cell[] neighbor) {
        this.neighbor = neighbor;
        for (Cell aNeighbor:neighbor) {
            if (aNeighbor.isLive()) numOfLiveNeighbor++;
        }
        numOfLiveNeighborBuffer = numOfLiveNeighbor;
    }

    public int getNumOfLiveNeighbor() {
        return numOfLiveNeighbor;
    }

    public void myNotify(Boolean moreOrLess) {
        for (Cell aNeighbor : neighbor) {
            aNeighbor.update(moreOrLess);
        }
    }

    private void update(Boolean moreOrLess) {
        if (moreOrLess) {
            numOfLiveNeighborBuffer++;
        } else{
            numOfLiveNeighborBuffer--;
        }
    }

    public void renewal() {
        boolean currentAttribute = isLive();
        if (numOfLiveNeighbor < 2 || numOfLiveNeighbor > 3) {
            die();
        } else if (numOfLiveNeighbor == 3){
            revive();
        }
        numOfLiveNeighbor = numOfLiveNeighborBuffer;

        if (currentAttribute != isLive()) {
            myNotify(isLive());
        }
    }

    public void setEdge(boolean isEdge) {
        this.isEdge = isEdge;
    }

    public boolean isEdge() {
        return isEdge;
    }

    public int getNumOfLiveNeighborBuffer() {
        return numOfLiveNeighborBuffer;
    }
}
