package org.example.team2msg.msg.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.msg.professor.dao.ProfessorDAO;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/proflogin")
@Log4j2
public class ProfessorloginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/professor/proflogin.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("ID");
        String ppw = req.getParameter("PASSWORD");

        //DB에서 사용자 정보를 확인해 정보를 얻어오기
        try {
            Optional<ProfessorVO> result = ProfessorDAO.INSTANCE.get(pid, ppw);
            result.ifPresentOrElse( professorVO -> {
                Cookie loginCookie = new Cookie("Profid",pid);
                loginCookie.setPath("/");
                loginCookie.setMaxAge(60*60*24);

                resp.addCookie(loginCookie);
                log.info("Received login request with ID: {} and PASSWORD: {}", pid, ppw);

                try {
                    log.info("SUCCEEDDDDDDDD LOIGIUN");
                    resp.sendRedirect("/proflist");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, () -> {
                try {
                    log.info("FAILED LOGIN");
                    resp.sendRedirect("/proflogin");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
