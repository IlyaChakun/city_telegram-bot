package by.chekun.bot;

import by.chekun.bot.command.Command;
import by.chekun.bot.command.factory.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static by.chekun.bot.BotHelperConstants.*;


@Service
public class CityBotHelper extends TelegramLongPollingBot {

    private static final Logger logger = LogManager.getLogger(CityBotHelper.class);
    @Value("${telegrambot.token}")
    private String token;
    @Value("${telegrambot.username}")
    private String botUserName;

    private final CommandFactory commandFactory;
    private final TelegramBotsApi telegramBotsApi;

    public CityBotHelper(CommandFactory commandFactory,
                         TelegramBotsApi telegramBotsApi) {
        this.commandFactory = commandFactory;
        this.telegramBotsApi = telegramBotsApi;
    }


    @Override
    public void onUpdateReceived(Update update) {
        String inMessage = getInMessage(update);
        String chatId = getChatId(update);
        Command command = commandFactory.findCommand(inMessage);
        String text = command.execute();
        sendMsg(chatId, text);
    }

    private String getInMessage(Update update) {
        return update.hasEditedMessage() ?
                update.getEditedMessage().getText() :
                update.getMessage().getText();
    }

    private String getChatId(Update update) {
        return update.hasEditedMessage() ?
                update.getEditedMessage().getChatId().toString() :
                update.getMessage().getChatId().toString();
    }

    private void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        setButtons(sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Error during sending message.", e);
        }
    }

    private void setButtons(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton(AVAILABLE_CITIES));
        keyboardFirstRow.add(new KeyboardButton(RANDOM_CITY));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton(HELP));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }


    @Override
    public String getBotToken() {
        return token;
    }

    @PostConstruct
    public void registryBot() {
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            logger.error("Error occurred during bot registration.", e);
        }
    }


}
