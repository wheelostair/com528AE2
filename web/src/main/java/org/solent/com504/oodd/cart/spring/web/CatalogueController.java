package org.solent.com504.oodd.cart.spring.web;

import java.io.Console;
import java.io.PrintWriter;
import java.io.StringWriter;
import static java.lang.System.console;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.dto.User;
import org.solent.com504.oodd.cart.model.dto.UserRole;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import static org.solent.com504.oodd.cart.spring.web.UserAndLoginController.LOG;
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
public class CatalogueController {

    final static Logger LOG = LogManager.getLogger(CatalogueController.class);

    // this could be done with an autowired bean
    //private ShoppingService shoppingService = WebObjectFactory.getShoppingService();
    @Autowired
    ShoppingService shoppingService = null;

    // note that scope is session in configuration
    // so the shopping cart is unique for each web session
    @Autowired
    ShoppingCart shoppingCart = null;

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
    @RequestMapping(value = "/catalogue", method = {RequestMethod.GET, RequestMethod.POST})
    public String cataloglist(@RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "stock", required = false) Integer stock,
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemUUID", required = false) String itemUuid,
            @RequestParam(name = "username", required = false) String userName,
            Model model, HttpSession session) {
        String errorMessage = "";
        String message = "";
        LOG.debug("adding new item=" + name);

        List<ShoppingItem> availableItems = new ArrayList();

        // get sessionUser from session
        User user = getSessionUser(session);
        model.addAttribute("user", user);

        if ("addNewItem".equals(action)) {
            if (name == null || name.trim().isEmpty()) {
                errorMessage = "you must enter an item name";
            } else {
                try {
                    ShoppingItem item = new ShoppingItem();
                    item.setName(name);
                    item.setPrice(price);
                    item.setStock(stock);
                    item = shoppingService.addNewItem(item);
                    LOG.debug("addStock created new item item=" + item);
                    message = "addStock created new item item=" + item.getName();
                } catch (Exception ex) {
                    errorMessage = "problem adding item." + ex.getMessage();
                }

            }
        }
        if ("activate".equals(action)) {
            try {
                
                
                LOG.debug("Item Activated: " + itemName);

            } catch (Exception ex) {
                errorMessage = "problem activating item." + ex.getMessage();
            }
        }

        if ("deactivate".equals(action)) {
            try {

                shoppingService.deactivateItems(itemUuid);
                LOG.debug("Item Deactivated: " + itemName);

            } catch (Exception ex) {
                errorMessage = "problem deactivating item." + ex.getMessage();
            }
        }
        if (user.getUserRole() == UserRole.ADMINISTRATOR) {
            availableItems = shoppingService.getAvailableItems();
        }
        if (user.getUserRole() != UserRole.ADMINISTRATOR) {
            availableItems = shoppingService.getActivatedItems();
        }

        int availableItemsSize = availableItems.size();
        // used to set tab selected
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("message", message);
        model.addAttribute("availableItemsSize", availableItemsSize);
        model.addAttribute("availableItems", availableItems);
        model.addAttribute("selectedPage", "catalogue");
        return "catalogue";

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
