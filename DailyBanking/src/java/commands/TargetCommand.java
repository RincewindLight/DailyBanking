package commands;

import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;

/**
 *
 * @author Aaron, Kris, Lars, Timea,
 * @08/11/2013
 */
public class TargetCommand implements Command {

    private final String target;
    private final String title;
    private final SecurityRole role;
    private String email = null;
    private long userid = 0;

    public TargetCommand(String target, String title, SecurityRole role) {
        this.target = target;
        this.title = title;
        this.role = role;
    }

    @Override
    public String execute(HttpServletRequest request) { 
        //if(breaks EVERYTHING!!!){delete;}
        email = request.getParameter("custemail");
        request.setAttribute("custemail", email);
        request.setAttribute("title", title);
        request.setAttribute("navigation", setNav(title));
        return target;
    }

    public SecurityRole getRole() {
        return role;
    }

    public String setNav(String tiltle) {
        String nav = null;

        switch (title) {

            //all
            case "Main Page":
                nav = "<a id=\"activetab\">Main Page</a>";
                break;
            case "Login Page":
                nav = "<a id=\"activetab\">Login</a>\n <a id=\"logouttab\" href=\"Controller?command=logout\">Log Out</a>";
                break;

            //customer
            case "Customer Index":
                nav = "<a id=\"activetab\">Menu</a>\n<a href=\"Controller?command=customerChatPage&custemail="+email+"\" target='_blank'>Chat</a>\n <a id=\"logouttab\" href=\"Controller?command=logout\">Log Out</a>";
                break;
            case "Your Details":
                nav = "<a href=\"Controller?command=customerIndex&custemail="+email+"\">Menu</a>\n<a href=\"Controller?command=customerViewAccounts&custemail="+email+"\"> Accounts</a>\n<a href=\"Controller?command=customerChatPage&custemail="+email+"\" target='_blank'>Chat</a>\n<a id=\"logouttab\" href=\"Controller?command=logout\">Log Out</a>";
                break;
            case "Chat":
                nav ="<a href=\"Controller?command=customerIndex&custemail="+email+"\">Menu</a>\n<a href=\"Controller?command=customerViewAccounts&custemail="+email+"\"> Accounts</a>\n<a href=\"Controller?command=customerChatPage&custemail="+email+"\" target='_blank'>Chat</a>\n<a id=\"logouttab\" href=\"Controller?command=logout\">Log Out</a>";
                break;
                    
//            case "Account Details":
//                nav = "<a href=\"Controller?command=customerIndex&username=" + username + "\">Menu</a>\n<a href=\"Controller?command=customerViewDetails&username=" + username + "&userid=" + userid + "\">Profile</a>\n<a id=\"logouttab\" href=\"Controller?command=logout\">Log Out</a>";
//                break;
            case "Your Account History":
                nav = "<a href=\"Controller?command=customerIndex&custemail="+email+"\">Menu</a>\n<a href=\"Controller?command=customerViewDetails&custemail="+email+"\">Profile</a>\n<a href=\"Controller?command=customerViewAccounts&custemail="+email+"\">Back to Accounts</a>\n<a href=\"Controller?command=customerChatPage&custemail="+email+"\" target='_blank'>Chat</a>\n<a id=\"logouttab\" href=\"Controller?command=logout\">Log Out</a>";
                break;
            case "Your Accounts":
                nav = "<a href=\"Controller?command=customerIndex&custemail="+email+"\">Menu</a>\n<a href=\"Controller?command=customerViewDetails&custemail="+email+"\">Profile</a>\n<a href=\"Controller?command=customerChatPage&custemail="+email+"\" target='_blank'>Chat</a>\n<a id=\"logouttab\" href=\"Controller?command=logout\">Log Out</a>";
                break;
            case "Customer Transaction":
                nav = "<a href=\"Controller?command=customerIndex&custemail="+email+"\">Menu</a>\n<a href=\"Controller?command=customerViewDetails&custemail="+email+"\">Profile</a>\n<a href=\"Controller?command=customerViewAccounts&custemail="+email+"\">Back to Accounts</a>\n<a href=\"Controller?command=customerChatPage&custemail="+email+"\" target='_blank'>Chat</a>\n<a id=\"logouttab\" href=\"Controller?command=logout\">Log Out</a>";
                break;
//                <a href=\"Controller?command=customerChatPage\">Chat</a>
                
            //bankTeller
            case "Add Customer":
                nav = "<a href=\"Controller?command=bankTellerIndex&username=" +email+ "\">Menu</a>\n <a href=\"Controller?command=listCustomers\">Customer List</a>\n <a id=\"activetab\">Add Customer</a>\n <a id=\"logouttab\" href=\"Controller?command=logout\">Log Out</a>";
                break;
            case "BankTeller Index":
                nav = "<a id=\"activetab\">Menu</a>\n <a href=\"Controller?lastName=&firstName=&email=&command=addCustomer\">Add Customer</a>\n <a href=\"Controller?command=listCustomers\">Customers List</a>\n <a href=\"Controller?command=listAccounts\">Accounts List</a>\n <a id=\"logouttab\" href=\"Controller?command=logout\">Log Out</a>";
                break;
            case "Edit Customer":
                nav = "<a href=\"Controller?command=bankTellerIndex&username="+email+"\">Menu</a>\n <a href=\"Controller?command=listCustomers\">Customer List</a>\n <a id=\"activetab\">Edit Customer</a>\n <a id=\"logouttab\" href=\"Controller?command=logout_command\">Log Out</a>";
                break;
            case "Customers List":
                nav = "<a href=\"Controller?command=bankTellerIndex&username="+email+"\">Menu</a>\n <a id=\"activetab\">Customer List</a>\n <a id=\"logouttab\" href=\"Controller?command=logout_command\">Log Out</a>";
                break;
            case "Accounts List":
                nav = "<a href=\"Controller?command=bankTellerIndex&username="+email+"\">Menu</a>\n <a id=\"activetab\">Account List</a>\n <a id=\"logouttab\" href=\"Controller?command=logout_command\">Log Out</a>";
                break;
            case "View Customer":
                nav = "<a href=\"Controller?command=bankTellerIndex&username="+email+"\">Menu</a>\n <a id=\"activetab\">Customer View</a>\n <a id=\"logouttab\" href=\"Controller?command=logout_command\">Log Out</a>";
                break;
            case "Account View":
                nav = "<a href=\"Controller?command=bankTellerIndex&username="+email+"\">Menu</a>\n <a href=\"Controller?command=listAccounts\">Accounts List</a>\n <a id=\"activetab\">Account View</a>\n <a id=\"logouttab\" href=\"Controller?command=logout_command\">Log Out</a>";
                break;
            case "Account History":
                nav = "<a href=\"Controller?command=bankTellerIndex&username="+email+"\">Menu</a>\n <a id=\"activetab\">Account History</a>\n <a id=\"logouttab\" href=\"Controller?command=logout_command\">Log Out</a>";
                break;
            case "Create Account":
                nav = "<a href=\"Controller?command=bankTellerIndex&username="+email+"\">Menu</a>\n <a id=\"activetab\">Create Account</a>\n <a id=\"logouttab\" href=\"Controller?command=logout_command\">Log Out</a>";
                break;
        }
        return nav;
    }
}
