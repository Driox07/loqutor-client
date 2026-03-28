package asanchezg.loqutorcli.script;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class FragmentSignature implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String language;
    private final String voice;
    private final String effect;
    private final String level;
    private final String format;
    private final String engine;

    public FragmentSignature(String language, String voice, String effect, String level, String format, String engine) {
        this.language = language;
        this.voice = voice;
        this.effect = effect;
        this.level = level;
        this.format = format;
        this.engine = engine;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.language);
        hash = 53 * hash + Objects.hashCode(this.voice);
        hash = 53 * hash + Objects.hashCode(this.effect);
        hash = 53 * hash + Objects.hashCode(this.level);
        hash = 53 * hash + Objects.hashCode(this.format);
        hash = 53 * hash + Objects.hashCode(this.engine);
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
        final FragmentSignature other = (FragmentSignature) obj;
        if (!Objects.equals(this.language, other.language)) {
            return false;
        }
        if (!Objects.equals(this.voice, other.voice)) {
            return false;
        }
        if (!Objects.equals(this.effect, other.effect)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.format, other.format)) {
            return false;
        }
        return Objects.equals(this.engine, other.engine);
    }
    
    
}
