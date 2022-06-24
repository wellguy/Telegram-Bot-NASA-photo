import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


public class Bot extends TelegramLongPollingBot {

    private static final String TOKEN = "5190578285:AAGl_II28aiz0DZwF_TEI6M67twsYXbARAI";
    private static final String USERNAME = "wellguy_bot";

    public Bot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        String chatId = null;
        String textMessage = null;

        if (message != null && message.hasText()) {
            chatId = Long.toString(message.getChatId());
            textMessage = update.getMessage().getText().toLowerCase();
        }

        if (textMessage.contains("nasa")) {
            try {
                NasaStart.start();

                //Создаем сообщение с фотографией
                SendPhoto msg = new SendPhoto();
                msg.setChatId(chatId);
                msg.setCaption(NasaStart.getCaption());
                msg.setPhoto(new InputFile().setMedia(NasaStart.getImageUrl()));

                execute(msg);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
