package am.aua.library.ui.components;

import am.aua.library.models.Student;
import am.aua.library.repositories.StudentRepositoryImpl;

import java.util.List;

public class StudentListPanel extends AbstractListPanel<Student> {
    private final StudentRepositoryImpl studentRepository;

    public StudentListPanel() {
        super();
        this.studentRepository = new StudentRepositoryImpl();
    }

    @Override
    public void onSearch(String query) {
        List<Student> students = studentRepository.findByNameContaining(query);
        this.getListModel().clear();
        this.getListModel().addAll(students);
    }
}
