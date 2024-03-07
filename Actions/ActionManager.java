package org.example.Actions;

import org.example.AccountType;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class ActionManager {

    public static final Map<AccountType, List<IMDBAction>> actionsOfUser = Map.ofEntries(
            entry(AccountType.REGULAR, new LinkedList<>(List.of(
                    new ActionDisplayProductionsDetails(),
                    new ActionDisplayActorsDetails(),
                    new ActionDisplayNotifications(),
                    new ActionSearchActor(),
                    new ActionSearchProduction(),
                    new ActionAddProductionToFavourites(),
                    new ActionAddActorToFavourites(),
                    new ActionCreateRequest(),
                    new ActionRemoveActorFromFavourites(),
                    new ActionRemoveProductionFromFavourites(),
                    new ActionAddRating(),
                    new ActionRemoveRating(),
                    new ActionLogOut()
            ))),
            entry(AccountType.CONTRIBUTOR, new LinkedList<>(List.of(
                    new ActionDisplayProductionsDetails(),
                    new ActionDisplayActorsDetails(),
                    new ActionDisplayNotifications(),
                    new ActionSearchActor(),
                    new ActionAddActor(),
                    new ActionRemoveProduction(),
                    new ActionRemoveActor(),
                    new ActionRezolveRequest(),
                    new ActionCreateRequest(),
                    new ActionUpdateActorInformation(),
                    new ActionUpdateProductionInformation(),
                    new ActionSearchProduction(),
                    new ActionAddProduction(),
                    new ActionDisplayRequests(),
                    new ActionAddProductionToFavourites(),
                    new ActionAddActorToFavourites(),
                    new ActionRemoveActorFromFavourites(),
                    new ActionRemoveProductionFromFavourites(),
                    new ActionLogOut()

            ))),
            entry(AccountType.ADMIN, new LinkedList<>(List.of(
                    new ActionDisplayProductionsDetails(),
                    new ActionDisplayActorsDetails(),
                    new ActionDisplayNotifications(),
                    new ActionSearchActor(),
                    new ActionAddActor(),
                    new ActionSearchProduction(),
                    new ActionAddProduction(),
                    new ActionRemoveProduction(),
                    new ActionRemoveActor(),
                    new ActionRezolveRequest(),
                    new ActionUpdateActorInformation(),
                    new ActionUpdateProductionInformation(),
                    new ActionDisplayRequests(),
                    new ActionAddProductionToFavourites(),
                    new ActionAddActorToFavourites(),
                    new ActionRemoveActorFromFavourites(),
                    new ActionRemoveProductionFromFavourites(),
                    new ActionAddUser(),
                    new ActionLogOut()
            )))
    );
}
