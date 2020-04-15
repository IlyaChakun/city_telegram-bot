package by.chekun.bot.command.impl;

import by.chekun.bot.command.Command;
import by.chekun.dto.CityDto;
import by.chekun.service.CityService;

import java.util.stream.Collectors;

public class FindAllCitiesCommand implements Command {

    private final CityService cityService;

    public FindAllCitiesCommand(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public String execute() {
        return cityService.findAll().stream()
                .map(CityDto::getName)
                .collect(Collectors.joining(",  "));
    }
}
