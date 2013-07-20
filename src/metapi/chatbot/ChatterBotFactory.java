package metapi.chatbot;

/**
 * Created with IntelliJ IDEA.
 * User: Jari
 * Date: 21.7.2013
 * Time: 0:53
 * To change this template use File | Settings | File Templates.
 */
public class ChatterBotFactory {

    public ChatterBot create(ChatterBotType type) throws Exception {
        return create(type, null);
    }

    public ChatterBot create(ChatterBotType type, Object arg) throws Exception {
        switch (type) {
            case CLEVERBOT:
                return new Cleverbot("http://www.cleverbot.com/webservicemin");
            case JABBERWACKY:
                return new Cleverbot("http://jabberwacky.com/webservicemin");
            case PANDORABOTS:
                if (arg == null) {
                    throw new Exception("PANDORABOTS needs a botid arg");
                }
                return new Pandorabots(arg.toString());
        }
        return null;
    }
}