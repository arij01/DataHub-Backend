package com.example.demo.Services;

import com.example.demo.Repository.ActionLogRepository;
import com.example.demo.Repository.DatasetsRepo;
import com.example.demo.entity.ActionLog;
import com.example.demo.entity.Datasets;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DatasetsService implements  IDatasetsService {

    private  DatasetsRepo datasetsRepo;
    private final ActionLogRepository actionLogRepository;

    public Datasets addFile(Long id, MultipartFile file) throws IOException {
        Datasets datasets = datasetsRepo.findById(id).orElse(null);
        if (datasets != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileExtension = StringUtils.getFilenameExtension(fileName);
            String generatedFileName = UUID.randomUUID().toString() + "." + fileExtension;

            Path filePath = Paths.get("uploads").resolve(generatedFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            datasets.setFileName(fileName);
            datasets.setFilePath(filePath.toString());

            datasetsRepo.save(datasets);
        }
        return datasets;
    }
    public String getDatasetNameById(Long id) {
        return datasetsRepo.findNomById(id);
    }


//    public List<Datasets> getAllDatasets() {
//
//        return datasetsRepo.findAll();
//    }
//
//    public Datasets getDatasetById(Long id) {
//
//        return datasetsRepo.findById(id).orElse(null);
//    }

//    public Datasets createDataset(Datasets dataset) {
//
//        return datasetsRepo.save(dataset);
//    }
    //logs section
public List<Datasets> getAllDatasets() {
    logAction("GET_ALL", "Datasets", null); // Log action for getting all datasets
    return datasetsRepo.findAll();
}

    public Datasets getDatasetById(Long id) {
        Datasets dataset = datasetsRepo.findById(id).orElse(null);
        logAction("GET_BY_ID", "Datasets", id); // Log action for getting dataset by ID
        return dataset;
    }
public Datasets createDataset(Datasets dataset) {
    Datasets createdDataset = datasetsRepo.save(dataset);
    logAction("CREATE", "Datasets", createdDataset.getId()); // Log creation action
    return createdDataset;
}

public Datasets updateDataset(Long id, Datasets updatedDataset) {
    if (datasetsRepo.existsById(id)) {
        updatedDataset.setId(id);
        Datasets updated = datasetsRepo.save(updatedDataset);
        logAction("UPDATE", "Datasets", id);
        return updated;
    } else {
        return null; // or throw an exception indicating dataset not found
    }
}

    public void deleteDataset(Long id) {
        if (datasetsRepo.existsById(id)) {
            datasetsRepo.deleteById(id);
            logAction("DELETE", "Datasets", id); // Log deletion action
        }
    }
//    private void logAction(String action, String entityName, Long entityId, String username) {
//        ActionLog log = ActionLog.builder()
//                .action(action)
//                .entityName(entityName)
//                .entityId(entityId)
//                .username(username) // Set username if applicable
//                .timestamp(LocalDateTime.now())
//                .build();
//        actionLogRepository.save(log);
//    }
private void logAction(String action, String entityName, Long entityId) {
    int userid = 1;
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//    String ipAddress6 = getIpAddress(request);
//    String ipAddress= getIpAddress(request);
// Assuming you have a method to get the client's IP address as an IPv6 string
    String ipv6Address = getClientIpAddress(request);

// Convert the IPv6 address to an IPv4 address
    String ipv4Address = "";
    try {
        ipv4Address = convertIpv6To4(ipv6Address);
    } catch (UnknownHostException | RuntimeException e) {
        // Handle the exception appropriately
        e.printStackTrace();
    }

// Now, ipv4Address contains the IPv4 equivalent of the IPv6 address
// You can save this string to your database
// Assuming you have a method to save the IP address to the database

    String httpMethod = getHttpMethod();
    String requestUri = getRequestUri();

    ActionLog log = ActionLog.builder()
            .action(action)
            .entityName(entityName)
            .entityId(entityId)
            .userid(userid)
            .ipAddress(ipv4Address)
            .httpMethod(httpMethod)
            .requestUri(requestUri)
            .timestamp(LocalDateTime.now())
            .build();
    actionLogRepository.save(log);
}

//    private String getUsername() {
//        String username = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof User) {
//            username = ((User) principal).getUsername();
//        } else if (principal instanceof String) {
//            username = (String) principal;
//        }
//        return username;
//    }

    private String getIpAddress(HttpServletRequest request) {
        String[] headersToCheck = {
                "X-Forwarded-For",
                "X-Real-IP",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR"
        };

        for (String header : headersToCheck) {
            String ip = request.getHeader(header);
            if (ip != null && ip.trim().length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
                // If multiple IP addresses are present in the header, return the first one
                return ip.split(",")[0].trim();
            }
        }

        // If none of the headers contain a valid IP address, fallback to the default method
        return request.getRemoteAddr();
    }

    public String convertIpv6To4(String ipv6) throws UnknownHostException, RuntimeException {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipv6);
            if (inetAddress instanceof Inet6Address) {
                byte[] ipv6Bytes = inetAddress.getAddress();
                byte[] ipv4Bytes = new byte[4];
                System.arraycopy(ipv6Bytes, 12, ipv4Bytes, 0, 4);
                InetAddress ipv4Address = InetAddress.getByAddress(ipv4Bytes);
                return ipv4Address.getHostAddress();
            } else {
                throw new RuntimeException("Not an IPv6 address.");
            }
        } catch (UnknownHostException e) {
            throw new UnknownHostException("Invalid IPv6 address");
        }
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress != null && ipAddress.length() > 0) {
            ipAddress = ipAddress.split(",")[0].trim();
        } else {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }



    private String getHttpMethod() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getMethod();
    }

    private String getRequestUri() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getRequestURI();
    }
    private static String convertIPv6ToIPv4(String ipv6Address) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipv6Address);
            if (inetAddress instanceof Inet6Address) {
                Inet6Address inet6Address = (Inet6Address) inetAddress;
                byte[] ipv4Bytes = new byte[4];
                // Extract the last 4 bytes from the IPv6 address to construct the IPv4 equivalent
                System.arraycopy(inet6Address.getAddress(), 12, ipv4Bytes, 0, 4);
                return InetAddress.getByAddress(ipv4Bytes).getHostAddress();
            } else {
                // If it's not an IPv6 address, return the original address
                return ipv6Address;
            }
        } catch (UnknownHostException e) {
            // Handle the case where the conversion fails
            e.printStackTrace();
            return ipv6Address; // Return the original address if conversion fails
        }
    }


    //logs section

//    public void deleteDataset(Long id) {
//        datasetsRepo.deleteById(id);
//    }
public Datasets addFileToDataset(Long id, MultipartFile file) throws IOException {
    Datasets dataset = datasetsRepo.findById(id).orElse(null);
    if (dataset != null) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = StringUtils.getFilenameExtension(fileName);
        String generatedFileName = UUID.randomUUID().toString() + "." + fileExtension;

        Path filePath = Paths.get("uploads").resolve(generatedFileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        dataset.setFileName(fileName);
        dataset.setFilePath(filePath.toString());

        // Enregistrer les modifications
        datasetsRepo.save(dataset);
    }
    return dataset;
}
    public Datasets addImageToDataset(Long id, MultipartFile image) throws IOException {
        Datasets dataset = datasetsRepo.findById(id).orElse(null);
        if (dataset != null) {
            String imageName = StringUtils.cleanPath(image.getOriginalFilename());
            String imageExtension = StringUtils.getFilenameExtension(imageName);
            String generatedImageName = UUID.randomUUID().toString() + "." + imageExtension;

            Path imagePath = Paths.get("uploads").resolve(generatedImageName);
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            dataset.setImageName(imageName);
            dataset.setImagePath(imagePath.toString());

            datasetsRepo.save(dataset);
        }
        return dataset;
    }
    public byte[] getImageForDataset(Long id) throws IOException {
        Datasets dataset = datasetsRepo.findById(id).orElse(null);
        if (dataset != null && dataset.getImagePath() != null) {
            Path imagePath = Paths.get(dataset.getImagePath());
            return Files.readAllBytes(imagePath);
        } else {
            throw new IOException("Image not found for dataset with ID: " + id);
        }
    }

}
