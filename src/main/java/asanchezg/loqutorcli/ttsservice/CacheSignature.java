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
    private final String format;
    private final String engine;
    
    public CacheSignature(String text, String language, String voice, String effect, String level, String format, String engine) {
        this.text = text;
        this.language = language;
        this.voice = voice;
        this.effect = effect;
        this.level = level;
        this.format = format;
        this.engine = engine;
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
               Objects.equals(this.level, other.level) &&
               Objects.equals(this.format, other.format) &&
               Objects.equals(this.engine, other.engine);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.text);
        hash = 37 * hash + Objects.hashCode(this.language);
        hash = 37 * hash + Objects.hashCode(this.voice);
        hash = 37 * hash + Objects.hashCode(this.effect);
        hash = 37 * hash + Objects.hashCode(this.level);
        hash = 37 * hash + Objects.hashCode(this.format);
        hash = 37 * hash + Objects.hashCode(this.engine);
        return hash;
    }
}
