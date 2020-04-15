package by.chekun.bot.command.impl;

import by.chekun.bot.command.Command;
import by.chekun.service.CityService;
import by.chekun.service.exception.ServiceException;

public class FindRandomCityCommand implements Command {

    private final CityService cityService;

    public FindRandomCityCommand(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public String execute() {
        try {
            return cityService.findRandomCity().toString();
        } catch (ServiceException ex) {
            return ex.getMessage();
        }
    }
}