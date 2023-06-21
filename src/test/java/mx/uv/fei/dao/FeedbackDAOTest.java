package mx.uv.fei.dao;

import mx.uv.fei.dao.implementations.AdvancementDAO;
import mx.uv.fei.dao.implementations.EvidenceDAO;
import mx.uv.fei.dao.implementations.ProjectDAO;
import mx.uv.fei.dao.implementations.UserDAO;
import mx.uv.fei.logic.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackDAOTest {

    @BeforeEach
    void setUp() throws SQLException {
        var projectDAO = new ProjectDAO();
        var project = new Project();

        project.setAcademicBodyId("UV-CA-127");
        project.setInvestigationProjectName("");
        project.setLGAC_Id(1);
        project.setInvestigationLine("Control estadistico de procesos");
        project.setApproximateDuration("6 meses");
        project.setModalityId(3);
        project.setReceptionWorkName("Control Estadístico de Procesos en el desarrollo de Software");
        project.setRequisites(
                "Capacidad de análisis y abstracción, Medición de Software, lectura de documentos en inglés.");
        project.setStudentsParticipating(1);
        project.setInvestigationProjectDescription("");
        project.setReceptionWorkDescription("La mejora del proceso de software basado en la medición es hoy en " +
                "día una actividad obligatoria. Esto implica seguimiento continuo del proceso para predecir su " +
                "comportamiento, resaltar sus variaciones de rendimiento y, si es necesario, reaccionar " +
                "rápidamente a él [1]. Las variaciones del proceso se deben a factores de causas comunes o " +
                "imputables. Los primeros forman parte del proceso en sí mismo, mientras que los segundos " +
                "se deben a eventos excepcionales que resultan en un comportamiento inestable del proceso " +
                "y por lo tanto en menos previsibilidad.\n" +
                "Por otro lado, el Control Estadístico de Procesos (SPC) es un enfoque basado en " +
                "estadísticas capaz de determinar si un proceso es estable o no, discriminando entre la " +
                "presencia de variación de causa común y variación de causa asignable. Esta técnica ha " +
                "demostrado ser efectiva en procesos de fabricación pero aún no en contextos de procesos de " +
                "desarrollo de software. Aquí la experiencia en el uso de SPC aún no está madura. Por lo " +
                "tanto, aún falta una comprensión clara de los resultados del SPC[1]. Aunque muchos " +
                "autores lo han utilizado en software, a menudo no han considerado las principales " +
                "diferencias entre la fabricación o manufactura, y las características del proceso de " +
                "software. Debido a tales diferencias, SPC no puede ser adoptado \"tal cual\", pero puede " +
                "adaptarse.\n" +
                "Por lo tanto, esta Revisión Sistemática de la Literatura tiene como objetivo, presentar el " +
                "estado actual de la aplicación del Control Estadístico de Procesos en el desarrollo de " +
                "Software, con el fin de establecer oportunidades para adoptarlo en el área de desarrollo de " +
                "Software.");
        project.setExpectedResults("- Reporte escrito de la RSL\n- Borrador artículo");
        project.setRecommendedBibliography("[1] Caivano, D. (2005, March). Continuous software process improvement " +
                "through statistical process control. In Ninth European Conference on Software Maintenance and " +
                "Reengineering (pp. 288-293). IEEE.\n" +
                "[2] Fernández-Corrales, C., Jenkins, M., & Villegas, J. (2013, October). Application of " +
                "statistical process control to software defect metrics: An industry experience report.\n" +
                "In 2013 ACM/IEEE International Symposium on Empirical Software Engineering and " +
                "Measurement (pp. 323-331). IEEE.\n" +
                "[3] Jacob, A. L., & Pillai, S. K. (2003). Statistical process control to improve coding and " +
                "code review. IEEE software, 20(3), 50-55.\n" +
                "[4] Maisikeli, S. G. (2020, November). Tracking Stability of Software Evolution Using " +
                "Statistical Process Control Limit. In 2020 Seventh International Conference on " +
                "Information Technology Trends (ITT) (pp. 156-160). IEEE.\n" +
                "[5]Zhang, Y., & Hou, L. (2010, June). Software Design for Quality-oriented Statistical " +
                "Tolerance and SPC. In 2010 Third International Conference on Information and " +
                "Computing (Vol. 3, pp. 127-130). IEEE.");

        projectDAO.addProject(project);

        var studentAccessAccount = new AccessAccount();
        var student = new Student();
        var userDAO = new UserDAO();

        studentAccessAccount.setUsername("Gerardo");
        studentAccessAccount.setUserPassword("contrasenagerardo");
        studentAccessAccount.setUserEmail("zs21050285@estudiantes.uv.mx");
        studentAccessAccount.setUserType("Estudiante");

        student.setUsername("Gerardo");
        student.setName("Gerardo");
        student.setLastName("Martinez Rebolledo");
        student.setStudentID("ZS21050285");

        userDAO.addStudentUserTransaction(studentAccessAccount, student);

        var professorAccessAccount = new AccessAccount();
        var professor = new Professor();

        professorAccessAccount.setUsername("angesanchez");
        professorAccessAccount.setUserPassword("contrasenaangesanchez");
        professorAccessAccount.setUserEmail("angesanchez@uv.mx");
        professorAccessAccount.setUserType("Profesor");

        professor.setProfessorName("Angel Juan");
        professor.setProfessorLastName("Sanchez Garcia");
        professor.setProfessorDegree("Dr.");
        professor.setUsername("angesanchez");

        userDAO.addProfessorUserTransaction(professorAccessAccount, professor);

        project.setDirectorName("Dr. Angel Juan Sanchez Gracia");
        projectDAO.setDirectorIDtoProject(project);

        var advancement = new Advancement();
        var advancementDAO = new AdvancementDAO();

        advancement.setProjectId(projectDAO.getProjectIDByTitle(
                "Control Estadístico de Procesos en el desarrollo de Software"));
        advancement.setAdvancementStartDate("2023-12-31");
        advancement.setAdvancementDeadline("2024-12-31");
        advancement.setAdvancementName("Avance para pruebas");
        advancement.setAdvancementDescription("Este avance se utilizará para realizar pruebas");

        advancementDAO.addAdvancement(advancement);

        var evidence = new Evidence();
        var evidenceDAO = new EvidenceDAO();

        evidence.setEvidenceTitle("Evidencia para prueba");
        evidence.setEvidenceDescription("Esta evidencia es entregada para una prueba");
        evidence.setAdvancementId(advancementDAO.getLastAdvancementID());
        evidence.setStudentId("ZS21050285");

        evidenceDAO.addEvidence(evidence);
    }

    @AfterEach
    void tearDown() throws SQLException {
        var projectDAO = new ProjectDAO();

        projectDAO.deleteProjectByID(projectDAO.getProjectIDByTitle(
                "Control Estadístico de Procesos en el desarrollo de Software"));

        var userDAO = new UserDAO();

        userDAO.deleteUserByUsername("Gerardo");
        userDAO.deleteUserByUsername("angesanchez");

    }

    @Test
    void testAddFeedbackSuccess() throws SQLException {

    }
}