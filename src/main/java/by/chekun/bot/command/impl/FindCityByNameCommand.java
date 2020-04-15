package by.chekun.bot.command.impl;

import by.chekun.bot.command.Command;
import by.chekun.service.CityService;
import by.chekun.service.exception.ResourceNotFoundException;

public class FindCityByNameCommand implements Command {

    private final String cityName;
    private final CityService cityService;

    public FindCityByNameCommand(String cityName,
                                 CityService cityService) {
        this.cityName = cityName;
        this.cityService = cityService;
    }

    @Override
    public String execute() {
        try {
            return cityService.findByName(cityName).getInfo();
        } catch (ResourceNotFoundException ex) {
            return ex.getMessage();
        }
    }
}
