package com.github.truongbb.hibernate.bus.driver.management.logic_handle;

import com.github.truongbb.hibernate.bus.driver.management.entity.Driver;
import com.github.truongbb.hibernate.bus.driver.management.repository.DriverRepository;
import com.github.truongbb.hibernate.bus.driver.management.util.DataUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DriverService implements DataInitializing {

    private List<Driver> drivers;

    private final DriverRepository driverRepository = new DriverRepository();

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public void init() {
        this.setDrivers(driverRepository.getAll());
    }

    public void showAll() {
        this.drivers.forEach(System.out::println);
    }

    public void createNew() {
        System.out.print("Xin mời nhập số tài xế muốn thêm mới: ");
        int driverNumber = -1;
        do {
            try {
                driverNumber = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Số tài xế cần nhập là một số nguyên, vui lòng nhập lại: ");
                continue;
            }
            if (driverNumber > 0) {
                break;
            }
            System.out.print("Số tài xế phải là số dương, vui lòng nhập lại: ");
        } while (true);

        List<Driver> newDrivers = new ArrayList<>();
        for (int i = 0; i < driverNumber; i++) {
            Driver driver = inputInfo();
            this.drivers.add(driver);
            newDrivers.add(driver);
        }
        this.driverRepository.saveAll(newDrivers);
    }

    private Driver inputInfo() {
        Driver driver = new Driver();
        System.out.println("Nhập tên của tài xế: ");
        driver.setName(new Scanner(System.in).nextLine());
        System.out.println("Nhập địa chỉ của tài xế: ");
        driver.setAddress(new Scanner(System.in).nextLine());
        System.out.println("Nhập SDT của tài xế: ");
        driver.setPhoneNumber(new Scanner(System.in).nextLine());
        driver.setDriverLevel(this.inputDriverLevel());
        return driver;
    }

    private String inputDriverLevel() {
        System.out.println("Nhập trình độ của tài xế, chọn 1 trong các trình độ dưới đây: ");
        System.out.println("1. Loại A");
        System.out.println("2. Loại B");
        System.out.println("3. Loại C");
        System.out.println("4. Loại D");
        System.out.println("5. Loại E");
        System.out.println("6. Loại F");
        System.out.print("Xin mời nhập lựa chọn: ");
        int level = -1;
        do {
            try {
                level = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Giá trị cần nhập là một số nguyên, vui lòng nhập lại: ");
                continue;
            }
            if (level >= 1 && level <= 9) {
                break;
            }
            System.out.print("Giá trị lựa chọn không tồn tại, vui lòng nhập lại: ");
        } while (true);
        switch (level) {
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
        }
        return null;
    }

    public boolean isEmptyDriver() {
        return DataUtil.isEmptyCollection(this.drivers);
    }

    public Driver findById(int driverId) {
        return driverRepository.findById(driverId);
    }
}
