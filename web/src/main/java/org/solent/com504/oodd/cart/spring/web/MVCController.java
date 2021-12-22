package org.solent.com504.oodd.cart.spring.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.TransactionRequestMessage;
import org.solent.com504.oodd.bank.model.dto.BankTransactionStatus;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;
import org.solent.com504.oodd.bank.model.client.BankRestClient;
import org.solent.com504.oodd.bank.client.impl.BankRestClientImpl;
import org.solent.com504.oodd.bank.model.dto.BankTransaction;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.dto.UserRole;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.solent.com504.oodd.cart.spring.service.PopulateDatabaseOnStart;
import org.solent.com504.oodd.cart.web.WebObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MVCController {

    final static Logger LOG = LogManager.getLogger(MVCController.class);

    // this could be done with an autowired bean
    //private ShoppingService shoppingService = WebObjectFactory.getShoppingService();
    @Autowired
    ShoppingService shoppingService = null;

    // note that scope is session in configuration
    // so the shopping cart is unique for each web session
    @Autowired
    ShoppingCart shoppingCart = null;

//    
//    @Autowired
//    TransactionRequestMessage transacetionRequestMessage = null;
    private User getSessionUser(HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            sessionUser = new User();
            sessionUser.setUsername("anonymous");
            sessionUser.setUserRole(UserRole.ANONYMOUS);
            session.setAttribute("sessionUser", sessionUser);
        }
        return sessionUser;
    }

    // this redirects calls to the root of our application to index.html
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
        return "redirect:/index.html";
    }

    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewCart(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemUUID", required = false) String itemUuid,
            Model model,
            HttpSession session) {

        // get sessionUser from session
        User user = getSessionUser(session);
        model.addAttribute("user", user);

        // used to set tab selected
        model.addAttribute("selectedPage", "home");

        String message = "";
        String errorMessage = "";

        // note that the shopping cart is is stored in the sessionUser's session
        // so there is one cart per sessionUser
//        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
//        if (shoppingCart == null) synchronized (this) {
//            if (shoppingCart == null) {
//                shoppingCart = WebObjectFactory.getNewShoppingCart();
//                session.setAttribute("shoppingCart", shoppingCart);
//            }
//        }
        if (action == null) {
            // do nothing but show page
        } else if ("addItemToCart".equals(action)) {
            ShoppingItem shoppingItem = shoppingService.getNewItemByName(itemName);

            LOG.debug("Quantity" + shoppingItem.getQuantity() + "Stock" + shoppingItem.getStock());
            if (shoppingItem == null) {
                message = "cannot add unknown " + itemName + " to cart";
            } else if (shoppingCart.addItemToCart(shoppingItem) == false) {
                message = "Not enough " + itemName + " to purchase";
            } else {
                message = "adding " + itemName + " to cart price= " + shoppingItem.getPrice();

            }
        } else if ("removeItemFromCart".equals(action)) {
            message = "removed " + itemName + " from cart";
            shoppingCart.removeItemFromCart(itemUuid);

        } else if ("addItemsToBasket".equals(action)) {
            message = "Added " + itemName + " to basket";
            shoppingCart.removeItemFromCart(itemUuid);
        } else {
            message = "unknown action=" + action;
        }

        List<ShoppingItem> availableItems = shoppingService.getAvailableItems();

        List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();

        Double shoppingcartTotal = shoppingCart.getTotal();

        // populate model with values
        model.addAttribute("availableItems", availableItems);
        model.addAttribute("shoppingCartItems", shoppingCartItems);
        model.addAttribute("shoppingcartTotal", shoppingcartTotal);
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "home";
    }

    @RequestMapping(value = "/about", method = {RequestMethod.GET, RequestMethod.POST})
    public String aboutCart(Model model, HttpSession session) {

        // get sessionUser from session
        User user = getSessionUser(session);
        model.addAttribute("user", user);

        // used to set tab selected
        model.addAttribute("selectedPage", "about");
        return "about";
    }

    @RequestMapping(value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
    public String contactCart(Model model, HttpSession session) {

        // get sessionUser from session
        User user = getSessionUser(session);
        model.addAttribute("user", user);

        // used to set tab selected
        model.addAttribute("selectedPage", "contact");
        return "contact";
    }

    @RequestMapping(value = "/basket", method = {RequestMethod.GET, RequestMethod.POST})
    public String basketCart(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemUUID", required = false) String itemUuid,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "cardnumber", required = false) String cardNumber,
            @RequestParam(name = "cvv", required = false) String cvv,
            @RequestParam(name = "issueNumber", required = false) String issueNumber,
            Model model,
            HttpSession session
    ) {

        // get sessionUser from session
        User user = getSessionUser(session);
        model.addAttribute("user", user);

        // used to set tab selected
        model.addAttribute("selectedPage", "basket");

        String message = "";
        String errorMessage = "";

        List<ShoppingItem> shoppingCartItems = shoppingCart.getShoppingCartItems();

        Double shoppingcartTotal = shoppingCart.getTotal();

        CreditCard fromCard = new CreditCard();
        fromCard.setEndDate("");
        fromCard.setCardnumber("");
        fromCard.setCvv("");
        fromCard.setIssueNumber("");

        CreditCard toCard = new CreditCard();
        toCard.setEndDate("11/21");
        toCard.setCardnumber("4285860000000021");
        toCard.setCvv("123");
        toCard.setIssueNumber("01");

        if (action == null) {
            // do nothing but show page
        } else if ("removeItemFromCart".equals(action)) {
            message = "removed " + itemName + " from cart";
            shoppingCart.removeItemFromCart(itemUuid);
            LOG.debug("Item Removed");

        } else if ("payment".equals(action)) {
            fromCard.setEndDate(endDate);
            fromCard.setCardnumber(cardNumber);
            fromCard.setCvv(cvv);
            fromCard.setIssueNumber(issueNumber);
            LOG.debug("card number: " + fromCard.getCardnumber());

            BankRestClient client = new BankRestClientImpl("http://com528bank.ukwest.cloudapp.azure.com:8080/rest");

            {
                Double amount = shoppingCart.getTotal();
                LOG.debug("amount: " + amount);
                TransactionReplyMessage result = client.transferMoney(toCard, fromCard, amount);
                message = "Transaction" + result;
            }
//            if (BankTransaction.BankTransactionStatus == "SUCCESS") {
//                shoppingCartItems.removeAll(shoppingCartItems);
//            } else {
//                message = "unknown action=" + action;
//            }
        }

        // populate model with values
        model.addAttribute(
                "shoppingCartItems", shoppingCartItems);
        model.addAttribute(
                "shoppingcartTotal", shoppingcartTotal);
        model.addAttribute(
                "message", message);
        model.addAttribute(
                "errorMessage", errorMessage);

        return "basket";
    }

    @RequestMapping(value = "/orders", method = {RequestMethod.GET, RequestMethod.POST})
    public String ordersCart(
            Model model, HttpSession session
    ) {

        // get sessionUser from session
        User user = getSessionUser(session);
        model.addAttribute("user", user);

        // used to set tab selected
        model.addAttribute("selectedPage", "orders");

        return "orders";
    }

    /*
     * Default exception handler, catches all exceptions, redirects to friendly
     * error page. Does not catch request mapping errors
     */
    @ExceptionHandler(Exception.class)
    public String myExceptionHandler(final Exception e, Model model,
            HttpServletRequest request
    ) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        final String strStackTrace = sw.toString(); // stack trace as a string
        String urlStr = "not defined";
        if (request != null) {
            StringBuffer url = request.getRequestURL();
            urlStr = url.toString();
        }
        model.addAttribute("requestUrl", urlStr);
        model.addAttribute("strStackTrace", strStackTrace);
        model.addAttribute("exception", e);
        //logger.error(strStackTrace); // send to logger first
        return "error"; // default friendly exception message for sessionUser
    }
}
