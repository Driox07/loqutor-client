package asanchezg.loqutorcli.script;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Adrián Sánchez Galera
 */
public class LoqutorScript implements Serializable {
    private static final long serialVersionUID = 1L;
    private final FragmentSignature defaultSignature;
    private Map<FragmentBounds, FragmentSignature> signatures;
    private String scriptText;
    
    public LoqutorScript(FragmentSignature defaultSignature){
        this.defaultSignature = defaultSignature;
        signatures = new HashMap<>();
    }
    
    public String getScriptText(){
        return scriptText;
    }
    
    public void setScriptText(String scriptText){
        this.scriptText = scriptText;
    }
    
    public void clearSignatureFromTo(int begginingIndex, int finalIndex){
        fillSignatureFromTo(begginingIndex, finalIndex, defaultSignature);
    }
    
    public void fillSignatureFromTo(int begginingIndex, int finalIndex, FragmentSignature fs){
        FragmentSignature currentSignature;
        Map<FragmentBounds, FragmentSignature> newSignatures = new HashMap<>(signatures);
        for(int i = begginingIndex; i <= finalIndex; i++){
            currentSignature = getFragmentSignatureAt(i);
            if(!Objects.equals(currentSignature, defaultSignature)){
                FragmentBounds ogFb = getFragmentBoundsAt(i);
                if(ogFb == null) continue;
                // Si quita un trozo del medio
                if(i > ogFb.getBegginingIndex() && ogFb.getFinalIndex() > finalIndex){
                    FragmentBounds lowFb = new FragmentBounds(ogFb.getBegginingIndex(), i-1);
                    FragmentBounds higFb = new FragmentBounds(finalIndex+1, ogFb.getFinalIndex());
                    newSignatures.put(lowFb, currentSignature);
                    newSignatures.put(higFb, currentSignature);
                    newSignatures.remove(ogFb);
                    break; // Si todo ha caido en medio de un fragmento es que ya terminó
                }
                // Si quita un trozo del inicio o entero
                if(i >= ogFb.getBegginingIndex() && finalIndex <= ogFb.getFinalIndex()){
                   newSignatures.remove(ogFb);
                   FragmentBounds newFb = new FragmentBounds(finalIndex + 1, ogFb.getFinalIndex());
                   if(newFb.getFinalIndex() >= newFb.getBegginingIndex()){
                       newSignatures.put(newFb, currentSignature);
                   }
                   break; // Limpiado hasta final del final index, terminada la limpia
                }
                // Si quita un trozo del final
                if(i > ogFb.getBegginingIndex() && ogFb.getFinalIndex() <= finalIndex){
                    newSignatures.remove(ogFb);
                    FragmentBounds newFb = new FragmentBounds(ogFb.getBegginingIndex(), i - 1);
                    if(newFb.getFinalIndex() >= newFb.getBegginingIndex()){
                        newSignatures.put(newFb, currentSignature);
                    }
                    i = ogFb.getFinalIndex();
                }
            }
        }
        if(!Objects.equals(fs,defaultSignature)) {
            FragmentBounds fillingFb = new FragmentBounds(begginingIndex, finalIndex);
            newSignatures.put(fillingFb, fs);
        }
        signatures = newSignatures;
    }
    
    public FragmentSignature getFragmentSignatureAt(int index){
        for(FragmentBounds fb : signatures.keySet()){
            if(fb.contains(index)) return signatures.get(fb);
        }
        return defaultSignature;
    }
    
    public FragmentBounds getFragmentBoundsAt(int index){
        for(FragmentBounds fb : signatures.keySet()){
            if(fb.contains(index)) return fb;
        }
        return null;
    }
    
}
