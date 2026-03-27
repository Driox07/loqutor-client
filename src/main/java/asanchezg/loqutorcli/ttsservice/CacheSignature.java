package asanchezg.loqutorcli.ttsservice;

import java.util.Objects;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class CacheSignature {
    private final String text;
    private final String language;
    private final String voice;
    private final String effect;
    private final String level;
    
    public CacheSignature(String text, String language, String voice, String effect, String level) {
        this.text = text;
        this.language = language;
        this.voice = voice;
        this.effect = effect;
        this.level = level;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        final CacheSignature other = (CacheSignature) obj;

        return Objects.equals(this.text, other.text) &&
               Objects.equals(this.language, other.language) &&
               Objects.equals(this.voice, other.voice) &&
               Objects.equals(this.effect, other.effect) &&
               Objects.equals(this.level, other.level);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.text);
        hash = 37 * hash + Objects.hashCode(this.language);
        hash = 37 * hash + Objects.hashCode(this.voice);
        hash = 37 * hash + Objects.hashCode(this.effect);
        hash = 37 * hash + Objects.hashCode(this.level);
        return hash;
    }
}
