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
    private final int    level;
    
    public CacheSignature(String text, String language, String voice, String effect, int level) {
        this.text = text;
        this.language = language;
        this.voice = voice;
        this.effect = effect;
        this.level = level;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final CacheSignature other = (CacheSignature) obj;
        return this.text.equals(other.text) &&
               this.language.equals(other.language) &&
               this.voice.equals(other.voice) &&
               this.effect.equals(other.effect) &&
               this.level == other.level;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.text);
        hash = 37 * hash + Objects.hashCode(this.language);
        hash = 37 * hash + Objects.hashCode(this.voice);
        hash = 37 * hash + Objects.hashCode(this.effect);
        hash = 37 * hash + this.level;
        return hash;
    }
}
