package metapi.chatbot;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 21.7.2013
 * Time: 0:51
 * To change this template use File | Settings | File Templates.
 */
public class ChatterBotThought {
    private String[] emotions;
    private String text;

    public String[] getEmotions() {
        return emotions;
    }

    public void setEmotions(String[] emotions) {
        this.emotions = emotions;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}