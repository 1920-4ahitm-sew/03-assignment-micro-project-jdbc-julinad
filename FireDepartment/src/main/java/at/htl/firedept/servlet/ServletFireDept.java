package at.htl.firedept.servlet;

import at.htl.firedept.database.Database;
import at.htl.firedept.model.Firetruck;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("servlet")
public class ServletFireDept extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Firetruck> fList = new LinkedList<>();
        fList = getFiretruckList();
        PrintWriter pw = resp.getWriter();

        //html head
        pw.println("<html><head><title>Firetruck List</title></head>");

        pw.println("<body><h1>List of Firetrucks</h1>");
        pw.println("<table><tr>" +
                    "<th>Type</th>" +
                    "<th>Licenseplate</th>"+
                    "<th>Number of seats</th>"+
                    "</tr>");
        for (Firetruck trucks : fList){
            pw.println("<tr><td>" + trucks.getType() + "</td>");
            pw.println("<td>" + trucks.getLicensePlate() + "</td>");
            pw.println("<td>" + trucks.getNumSeats()+ "</td></tr>");
        }

        pw.println("</table></body></html>");

    }

    public List<Firetruck> getFiretruckList(){
        List<Firetruck> fList = new LinkedList<>();
        Database db = new Database();
        try {
            fList = db.getFiretrucks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fList;
    }
}
