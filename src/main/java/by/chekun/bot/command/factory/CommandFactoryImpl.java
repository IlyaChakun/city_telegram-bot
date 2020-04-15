package by.chekun.bot.command.factory;

import by.chekun.bot.command.Command;
import by.chekun.bot.command.impl.*;
import by.chekun.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static by.chekun.bot.BotHelperConstants.*;

@Component
public class CommandFactoryImpl implements CommandFactory {

    private final CityService cityService;

    @Autowired
    public CommandFactoryImpl(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public Command findCommand(String action) {
        switch (action) {
            case START:
                return new StartCommand();
            case AVAILABLE_CITIES:
                return new FindAllCitiesCommand(cityService);
            case RANDOM_CITY:
                return new FindRandomCityCommand(cityService);
            case HELP:
                return new HelpCommand();
            default:
                return new FindCityByNameCommand(action, cityService);
        }
    }
}
