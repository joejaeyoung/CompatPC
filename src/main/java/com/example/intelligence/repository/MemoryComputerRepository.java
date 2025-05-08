package com.example.intelligence.repository;

import com.example.intelligence.domain.hardware.*;
import com.example.intelligence.exception.respository.FindNullErrorCode;
import com.example.intelligence.exception.respository.FindNullException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemoryComputerRepository implements ComputerRepository {
    ConcurrentLinkedQueue<Cases> casesDataBase = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Cooler>  coolerDataBase = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<CPU> cpuDataBase = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<GPU> gpuDataBase = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<HDD>  hddDataBase = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Mainboard> mainBoardDataBase = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<PowerSupply>  powerSupplyDataBase = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<RAM> ramDataBase = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<SSD>  ssdDataBase = new ConcurrentLinkedQueue<>();

    @Override
    public List<Cases> getCases() {
        if (casesDataBase.peek() == null)
            throw new FindNullException(FindNullErrorCode.NOT_FOUND_CASE);
        return casesDataBase.stream().toList();
    }

    @Override
    public List<Cooler> getCoolers() {
        if (coolerDataBase.peek() == null)
            throw new FindNullException(FindNullErrorCode.NOT_FOUND_COOLER);
        return coolerDataBase.stream().toList();
    }

    @Override
    public List<CPU> getCPUs() {
        if (cpuDataBase.peek() == null)
            throw new FindNullException(FindNullErrorCode.NOT_FOUND_CPU);
        return cpuDataBase.stream().toList();
    }

    @Override
    public List<GPU> getGPUs() {
        if (gpuDataBase.peek() == null)
            throw new FindNullException(FindNullErrorCode.NOT_FOUND_GPU);
        return gpuDataBase.stream().toList();
    }

    @Override
    public List<HDD> getHDDs() {
        if (hddDataBase.peek() == null)
            throw new FindNullException(FindNullErrorCode.NOT_FOUND_HDD);
        return hddDataBase.stream().toList();
    }

    @Override
    public List<Mainboard> getMainboards() {
        if (mainBoardDataBase.peek() == null)
            throw new FindNullException(FindNullErrorCode.NOT_FOUND_MAINBOARD);
        return mainBoardDataBase.stream().toList();
    }

    @Override
    public List<PowerSupply> getPowerSupply() {
        if (powerSupplyDataBase.peek() == null)
            throw new FindNullException(FindNullErrorCode.NOT_FOUND_POWERSUPPLY);
        return powerSupplyDataBase.stream().toList();
    }

    @Override
    public List<RAM> getRAMs() {
        if (ramDataBase.peek() == null)
            throw new FindNullException(FindNullErrorCode.NOT_FOUND_RAM);
        return ramDataBase.stream().toList();
    }

    @Override
    public List<SSD> getSSDs() {
        if (ssdDataBase.peek() == null)
            throw new FindNullException(FindNullErrorCode.NOT_FOUND_SSD);
        return ssdDataBase.stream().toList();
    }

    @Override
    public Cases getCaseByName(String name) {
        return casesDataBase.stream()
                    .filter(c -> c.getName().equals(name))
                    .findFirst()
                    .orElse(null);
    }

    /*
    @todo 지금은 memory db를 사용해서 stream 순회를 해야 하는데 DB 사용해서 얼른 교체 해야 합니답
     */
    @Override
    public Cooler getCoolerByName(String name) {
        return coolerDataBase.stream()
                    .filter(c -> c.getName().equals(name))
                    .findFirst()
                    .orElse(null);
    }

    @Override
    public CPU getCPUByName(String name) {
        return cpuDataBase.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public GPU getGPUByName(String name) {
        return gpuDataBase.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public HDD getHDDByName(String name) {
        return hddDataBase.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Mainboard getMainboardByName(String name) {
        return mainBoardDataBase.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public PowerSupply getPowerSupplyByName(String name) {
        return powerSupplyDataBase.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public RAM getRAMByName(String name) {
        return ramDataBase.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public SSD getSSDByName(String name) {
        return ssdDataBase.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveCase(Cases cases) {
        casesDataBase.add(cases);
    }

    @Override
    public void saveCooler(Cooler coolers) {
        coolerDataBase.add(coolers);
    }

    @Override
    public void saveCPU(CPU cpus) {
        cpuDataBase.add(cpus);
    }

    @Override
    public void saveGPU(GPU gpus) {
        gpuDataBase.add(gpus);
    }

    @Override
    public void saveHDD(HDD hdds) {
        hddDataBase.add(hdds);
    }

    @Override
    public void saveMainoard(Mainboard mainboards) {
        mainBoardDataBase.add(mainboards);
    }

    @Override
    public void savePowerSupply(PowerSupply powerSupply) {
        powerSupplyDataBase.add(powerSupply);
    }

    @Override
    public void saveRAM(RAM ram) {
        ramDataBase.add(ram);
    }

    @Override
    public void saveSSD(SSD ssd) {
        ssdDataBase.add(ssd);
    }
}
