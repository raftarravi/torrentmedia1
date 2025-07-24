package com.torrentmedia.torrentmedia.repository;

import com.torrentmedia.torrentmedia.entity.ContactUsForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUs extends JpaRepository<ContactUsForm,Long> {


}
