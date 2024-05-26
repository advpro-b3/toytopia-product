package id.ac.ui.cs.advprog.toytopiaproduct.repository;

import id.ac.ui.cs.advprog.toytopiaproduct.model.MyStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyStoreRepository extends JpaRepository<MyStore, Long> {
    MyStore findMyStoreById(Long userId);
}
