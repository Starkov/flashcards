package example.console;

import example.exception.DeckIsUnavailableException;
import example.exception.InitializationException;
import example.model.Card;
import example.model.Deck;
import example.repository.impl.CardRepositoryImpl;
import example.repository.impl.DeckRepositoryImpl;
import example.service.CardService;
import example.service.DeckService;
import example.service.impl.CardServiceImpl;
import example.service.impl.DeckServiceImpl;
import example.util.ModelLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Anatolii_Starkov on 11/29/2016.
 */
public class Application {
    private static final String DIRECTORY_WITH_DECKS_MSG = "Please set directory with decks";
    private static final String SOMETHING_WRONG_REASON_MSG = "Something wrong! Reason: ";
    private static final String DECK_IS_UNAVAILABLE_MSG = "Deck is unavailable. Please check it in path: ";
    private static final String SHOW = "s";
    private static final String NEXT = "n";
    private static final String CLOSE_DECK = "c";
    private static final String UNKNOWN_COMMAND = "Unknown command!";
    private static final String BAD_DECK_NAME = "Bad deck name";

    private CardService cardService = new CardServiceImpl(CardRepositoryImpl.getInstance());
    private DeckService deckService = new DeckServiceImpl(DeckRepositoryImpl.getInstance(), CardRepositoryImpl.getInstance());
    private ModelLoader modelLoader = new ModelLoader(deckService, cardService);

    public static void main(String[] args) {
        Application app = new Application();
        try {
            app.init(args);
        } catch (RuntimeException | InitializationException e) {
            System.out.println(SOMETHING_WRONG_REASON_MSG + Objects.toString(e.getMessage(), ""));
            return;
        }
        app.run();
    }

    /**
     * Initialize the application from files. Each file represent a list of words, each line in the file has front side
     * of a card and back side. Delimiter between words is ";". Each filename represent a deck name.
     *
     * @param args path to decks
     * @throws InitializationException
     */
    private void init(String[] args) throws InitializationException {
        if (args.length == 0) {
            throw new InitializationException(DIRECTORY_WITH_DECKS_MSG);
        }
        String decksPathRoot = args[0];
        List<Path> decksPath = loadPathsDecks(decksPathRoot);
        if (decksPath.isEmpty()) {
            throw new InitializationException("Directory with decks is empty!");
        }
        loadDecks(decksPath);
    }

    private void loadDecks(List<Path> decksPath) throws DeckIsUnavailableException {
        for (Path path : decksPath) {
            Deck deck = modelLoader.loadDeck(path.getFileName().toString());
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                reader.lines().forEach(line -> modelLoader.loadCard(line, deck));
            } catch (IOException e) {
                throw new DeckIsUnavailableException(DECK_IS_UNAVAILABLE_MSG + path.toString());
            }
        }
    }

    private List<Path> loadPathsDecks(String decksPathRoot) throws InitializationException {
        try {
            return Files.list(Paths.get(decksPathRoot)).filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            throw new InitializationException(Objects.toString(e.getMessage(), ""));
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--------------------------");
            System.out.println("chose a deck...");
            deckService.getAll().forEach(deck -> System.out.println(deck.getName()));
            String deckName = scanner.next();

            if (deckService.get(deckName) != null) {
                for (Card card : deckService.get(deckName).getCards()) {
                    System.out.println(card.getFront());
                    String command = scanner.next();
                    if (command.equals(SHOW)) {
                        System.out.println(card.getBack());
                        continue;
                    }
                    if (command.equals(NEXT)) {
                        continue;
                    }
                    if (command.equals(CLOSE_DECK)) {
                        break;
                    }
                    System.out.println(UNKNOWN_COMMAND);
                }
            } else {
                System.out.println(BAD_DECK_NAME);
            }
        }
    }
}
