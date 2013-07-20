package metapi.chatbot;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 21.7.2013
 * Time: 0:52
 * To change this template use File | Settings | File Templates.
 */
public interface ChatterBotSession {

    ChatterBotThought think(ChatterBotThought thought) throws Exception;

    String think(String text) throws Exception;
}
