package com.example.intelligence.repository;

import com.example.intelligence.domain.hardware.*;

import java.util.List;

public interface ComputerRepository {
    List<Cases> getCases();
    List<Cooler> getCoolers();
    List<CPU> getCPUs();
    List<GPU> getGPUs();
    List<HDD> getHDDs();
    List<Mainboard> getMainboards();
    List<PSU> getPowerSupply();
    List<RAM> getRAMs();
    List<SSD> getSSDs();

    Cases getCaseByName(String name);
    Cooler getCoolerByName(String name);
    CPU getCPUByName(String name);
    GPU getGPUByName(String name);
    HDD getHDDByName(String name);
    Mainboard getMainboardByName(String name);
    PSU getPowerSupplyByName(String name);
    RAM getRAMByName(String name);
    SSD getSSDByName(String name);

    void saveCase(Cases cases);
    void saveCooler(Cooler coolers);
    void saveCPU(CPU cpus);
    void saveGPU(GPU gpus);
    void saveHDD(HDD hdds);
    void saveMainoard(Mainboard mainboards);
    void savePowerSupply(PSU PSU);
    void saveRAM(RAM ram);
    void saveSSD(SSD ssd);
}
