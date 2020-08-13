package ru.tishtech.algorithmreviewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tishtech.algorithmreviewer.model.Bubble;

public interface BubbleRepository extends JpaRepository<Bubble, Long> {
}
