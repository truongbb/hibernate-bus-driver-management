package com.github.truongbb.hibernate.bus.driver.management.logic_handle;

import com.github.truongbb.hibernate.bus.driver.management.entity.BusLine;
import com.github.truongbb.hibernate.bus.driver.management.repository.BusLineRepository;
import com.github.truongbb.hibernate.bus.driver.management.util.DataUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BusLineService implements DataInitializing {

    private List<BusLine> busLines;

    private final BusLineRepository busLineRepository = new BusLineRepository();

    public List<BusLine> getBusLines() {
        return busLines;
    }

    public void setBusLines(List<BusLine> busLines) {
        this.busLines = busLines;
    }

    @Override
    public void init() {
        this.setBusLines(busLineRepository.getAll());
    }

    public void showAll() {
        this.busLines.forEach(System.out::println);
    }

    public void createNew() {
        System.out.println("Xin mời nhập số tuyến xe mới muốn thêm: ");
        int busLineNumber = -1;
        do {
            try {
                busLineNumber = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Số xe muốn thêm cần nhập là một số nguyên, vui lòng nhập lại: ");
                continue;
            }
            if (busLineNumber > 0) {
                break;
            }
            System.out.print("Số xe muốn thêm phải là số dương, vui lòng nhập lại: ");
        } while (true);

        List<BusLine> newBusLines = new ArrayList<>();
        for (int i = 0; i < busLineNumber; i++) {
            BusLine busLine = inputInfo();
            this.busLines.add(busLine);
            newBusLines.add(busLine);
        }

        this.busLineRepository.saveAll(newBusLines);
    }

    private BusLine inputInfo() {
        BusLine busLine = new BusLine();
        System.out.println("Nhập khoảng cách của tuyến xe: ");
        float distance = -1;
        do {
            try {
                distance = new Scanner(System.in).nextFloat();
            } catch (InputMismatchException ex) {
                System.out.print("Khoảng cách phải là một số, vui lòng nhập lại: ");
                continue;
            }
            if (distance > 0) {
                break;
            }
            System.out.print("Khoảng cách phải là một số dương, vui lòng nhập lại: ");
        } while (true);
        busLine.setDistance(distance);

        System.out.println("Nhập số điểm dừng của tuyến xe: ");
        float stopStationNumber = -1;
        do {
            try {
                stopStationNumber = new Scanner(System.in).nextFloat();
            } catch (InputMismatchException ex) {
                System.out.print("Số điểm dừng phải là một số, vui lòng nhập lại: ");
                continue;
            }
            if (stopStationNumber > 0) {
                break;
            }
            System.out.print("Số điểm dừng phải là một số dương, vui lòng nhập lại: ");
        } while (true);
        busLine.setStopStationNumber(stopStationNumber);
        return busLine;
    }

    public boolean isEmptyBusLine() {
        return DataUtil.isEmptyCollection(this.busLines);
    }

    public BusLine findById(int busLineId) {
        return busLineRepository.findById(busLineId);
    }
}
