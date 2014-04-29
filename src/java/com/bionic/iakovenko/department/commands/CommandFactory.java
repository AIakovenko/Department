package com.bionic.iakovenko.department.commands;

import com.bionic.iakovenko.department.commands.client.CommitRequestCommand;
import com.bionic.iakovenko.department.commands.client.PrepareRequestCommand;
import com.bionic.iakovenko.department.commands.client.SendRequestCommand;
import com.bionic.iakovenko.department.commands.client.ShowOwnRequestsCommand;
import com.bionic.iakovenko.department.commands.dispatcher.ChoiceUndoneRequest;
import com.bionic.iakovenko.department.commands.dispatcher.CommitWorkGroupCommand;
import com.bionic.iakovenko.department.commands.dispatcher.FormingWorkGroupCommand;
import com.bionic.iakovenko.department.commands.dispatcher.SearchRequest;
import com.bionic.iakovenko.department.commands.dispatcher.ShowDetailsCommand;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 * @autor Alex Iakovenko
 * Date: 4/10/14
 * Time: 10:41 PM
 */
public class CommandFactory {
    private static CommandFactory instance = null;
    private HashMap<String, ICommand> commandsMap;

    private CommandFactory(){
        commandsMap = new HashMap<String, ICommand>();
        commandsMap.put("login", new LoginCommand());
        commandsMap.put("registration", new RegistrationCommand());
        commandsMap.put("registrationForm", new RegistrationFormCommand());
        commandsMap.put("registrationCommit", new RegistrationCommitCommand());
        commandsMap.put("goMenu", new GoMenuCommand());
        commandsMap.put("error", new ErrorCommand());
        commandsMap.put("prepareRequest", new PrepareRequestCommand());
        commandsMap.put("sendRequest", new SendRequestCommand());
        commandsMap.put("commitRequest", new CommitRequestCommand());
        commandsMap.put("choiceUndoneRequest", new ChoiceUndoneRequest());
        commandsMap.put("formingGroup", new FormingWorkGroupCommand());
        commandsMap.put("commitWorkGroup", new CommitWorkGroupCommand());
        commandsMap.put("logOut", new LogOutCommand());
        commandsMap.put("searchRequest", new SearchRequest());
        commandsMap.put("wellDone", new WellDoneCommand());
        commandsMap.put("showDetails", new ShowDetailsCommand());
        commandsMap.put("showOwnRequests", new ShowOwnRequestsCommand());
     
    }

    public ICommand getCommand(HttpServletRequest request){
        String action = (String) request.getAttribute("command");
        ICommand command = commandsMap.get(action);
        if (command == null){
            command = new NoCommand();
        }
        return command;
    }
    public static CommandFactory getInstance(){
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;

    }
}
