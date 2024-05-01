package am.aua.library.ui;

import am.aua.library.repositories.ProfessorRepositoryImpl;
import am.aua.library.repositories.StudentRepositoryImpl;
import am.aua.library.ui.core.Page;

public class DatabaseUI extends Page {
    private final StudentRepositoryImpl studentRepository;
    private final ProfessorRepositoryImpl professorRepository;
    private Long id;

    public DatabaseUI(Long id) {
        super();
        this.studentRepository = new StudentRepositoryImpl();
        this.professorRepository = new ProfessorRepositoryImpl();
        if (studentRepository.exists(id)) {
            this.setTitle("Student View");
            return;
        }

        if (professorRepository.exists(id)) {
            this.setTitle("Professor View");
            return;
        }

        throw new RuntimeException("Invalid ID was provided. User doesn't exist");
    }

    @Override
    protected void setupPage() {

    }

    @Override
    protected void setupComponents() {

    }
}
