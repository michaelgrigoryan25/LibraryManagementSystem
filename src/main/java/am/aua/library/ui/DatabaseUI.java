package am.aua.library.ui;

import am.aua.library.repositories.ProfessorRepositoryImpl;
import am.aua.library.repositories.StudentRepositoryImpl;
import am.aua.library.ui.core.Page;

public class DatabaseUI extends Page {
    private final Long id;
    private final StudentRepositoryImpl studentRepository;
    private final ProfessorRepositoryImpl professorRepository;

    public DatabaseUI(Long id) {
        super();
        this.id = id;
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
