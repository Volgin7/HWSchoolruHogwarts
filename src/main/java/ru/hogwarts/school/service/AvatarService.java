package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    private static final Logger logger = LoggerFactory.getLogger(AvatarService.class);
    @Value("${student.avatar.dir.path}")
    private String avatarsDir;
 //   @Autowired
    private final StudentService studentService;
    @Autowired
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    public String getAvatarsDir() {
        return avatarsDir;
    }

    public void setAvatarsDir(String avatarsDir) {
        this.avatarsDir = avatarsDir;
    }


    public void uploadAvatar(Long id, MultipartFile file) throws IOException {
        logger.info("Was invoked method for upload avatar");
        Student student = studentService.findStudent(id);

        Path filePath = Path.of(avatarsDir, id + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try(InputStream is = file.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
            ) {
            bis.transferTo(bos);
            }

        Avatar avatar = findAvatar(id);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(generateImagePreview(filePath));

        avatarRepository.save(avatar);
    }

    public Avatar findAvatar(Long id) {
        logger.info("Was invoked method for find avatar by id");
        return avatarRepository.findByStudentId(id).orElse(new Avatar());
    }


    private byte[] generateImagePreview(Path filePath) throws IOException {
        try ( InputStream is = Files.newInputStream(filePath);
              BufferedInputStream bis = new BufferedInputStream(is, 1024);
              ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                  BufferedImage image = ImageIO.read(bis);

                  int height = image.getHeight() / (image.getWidth() / 100 );
                  BufferedImage preview = new BufferedImage(100, height, image.getType() );
                  Graphics2D graphics = preview.createGraphics();
                  graphics.drawImage(image, 0, 0, 100, height, null);
                  graphics.dispose();

                  ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
                  return baos.toByteArray();
              }



    }
    private String getExtension(String fileName) { return fileName.substring(fileName.lastIndexOf(".") + 1);}

    public Collection<Avatar> findAllAvatars(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method for find all avatars");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1,pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }

}
