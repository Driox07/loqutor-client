package asanchezg.loqutorcli.script;

import java.io.Serializable;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class FragmentBounds implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int begginingIndex;
    private final int finalIndex;

    public FragmentBounds(int begginingIndex, int finalIndex) {
        this.begginingIndex = begginingIndex;
        this.finalIndex = finalIndex;
    }

    public int getBegginingIndex() {
        return begginingIndex;
    }

    public int getFinalIndex() {
        return finalIndex;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.begginingIndex;
        hash = 53 * hash + this.finalIndex;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FragmentBounds other = (FragmentBounds) obj;
        if (this.begginingIndex != other.begginingIndex) {
            return false;
        }
        return this.finalIndex == other.finalIndex;
    }

    public boolean contains(int index){
        return begginingIndex <= index && index <= finalIndex;
    }
    
}
