package nasobesapi.workers;

import nasobesapi.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TelegramBotService extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    @Autowired
    public TelegramBotService(BotConfig config) {
        this.botConfig = config;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName(); /* * гетеры дает декоратор @Data*/
    }

    @Override
    public String getBotToken () {
        return botConfig.getBotToken(); /* * гетеры дает декоратор @Data*/
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            /** Входящие данные из чата */
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
//                    this.usersService.createUser(chatId); /** create new user */
                    onInfo(chatId);
                    break;
                case "/info":
//                    this.usersService.updateActivityUser(chatId);
                    onInfo(chatId);
                    break;
                case "/frontend_js":
//                    this.usersService.updateActivityUser(chatId);
//                    onPulse(chatId, "");
                    onOpenWebApplication(chatId, "frontend-js");
                    break;
                case "/frontend_react":
//                    this.usersService.updateActivityUser(chatId);
//                    onPulse(chatId, "");
                    onOpenWebApplication(chatId, "frontend-react");
                    break;
                case "/frontend_angular":
//                    this.usersService.updateActivityUser(chatId);
//                    onPulse(chatId, "");
                    onOpenWebApplication(chatId, "frontend-angular");
                    break;
                case "/stop":
                    break;
                default:
                    onDefault(chatId);
                    break;
            }
        }
    }

    private void onInfo(long chatId) {
        String message = "тестовая инфа";
        sendMessage(chatId, message);
    }

    private void onDefault(long chatId) {
        String message = "Команда не распознана";
        sendMessage(chatId, message);
    }

    private void onOpenWebApplication(long chatId, String devSpecification) {
        String answer = "Начать подготовку...";

        /** Создаем объект клавиатуры*/
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        /** Создаем кнопку web_app*/
        WebAppInfo linkForWebApi = new WebAppInfo("https://na-sobes.ru/" + devSpecification);

        InlineKeyboardButton webAppButton = new InlineKeyboardButton();
        webAppButton.setText("Open");
        webAppButton.setWebApp(linkForWebApi);

        /** Добавляем кнопку в список кнопок клавиатуры*/
        List<InlineKeyboardButton> keyboardRow = new ArrayList<>();
        keyboardRow.add(webAppButton);
        keyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));

        /** Отправляем сообщение с клавиатурой кнопок*/
        sendMessageWithKeyboard(chatId, answer, keyboardMarkup);
    }

    private void sendMessageWithKeyboard(long chatId, String textToSend, InlineKeyboardMarkup keyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        message.setReplyMarkup(keyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(long chatId, String textToSend) {
        SendMessage message  = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            execute(message);
        }  catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
