#include <stdio.h>
#include <stdlib.h>

#define MAX_PCB 100 // maximum number of PCBs

struct PCB {
    int processID;
    int parentIndex;
    int numChildren;
    int children[MAX_PCB]; // array of indices of children processes
};

struct PCB PCBList[MAX_PCB]; // array of PCBs

void initialize() {
    // initialize PCBList
    for (int i = 0; i < MAX_PCB; i++) {
        PCBList[i].processID = -1;
        PCBList[i].parentIndex = -1;
        PCBList[i].numChildren = 0;
        for (int j = 0; j < MAX_PCB; j++) {
            PCBList[i].children[j] = -1;
        }
    }
}

int allocatePCB() {
    // find a free PCB in PCBList
    for (int i = 0; i < MAX_PCB; i++) {
        if (PCBList[i].processID == -1) {
            PCBList[i].processID = i;
            return i;
        }
    }
    return -1; // no free PCB available
}

void create(int p) {
    int q = allocatePCB(); // allocate a free PCB
    PCBList[q].parentIndex = p; // record parent's index
    PCBList[p].numChildren++; // increment parent's numChildren
    PCBList[p].children[PCBList[p].numChildren - 1] = q; // add q to parent's children array
}

void destroy(int p) {
    // destroy all descendent processes
    for (int i = 0; i < PCBList[p].numChildren; i++) {
        destroy(PCBList[p].children[i]);
    }
    // free PCB
    PCBList[p].processID = -1;
    PCBList[p].parentIndex = -1;
    PCBList[p].numChildren = 0;
    for (int i = 0; i < MAX_PCB; i++) {
        PCBList[p].children[i] = -1;
    }
    // remove p from parent's children array
    if (PCBList[PCBList[p].parentIndex].numChildren > 0) {
        int i;
        for (i = 0; i < PCBList[PCBList[p].parentIndex].numChildren; i++) {
            if (PCBList[PCBList[p].parentIndex].children[i] == p) {
                break;
            }
        }
        for (int j = i; j < PCBList[PCBList[p].parentIndex].numChildren - 1; j++) {
            PCBList[PCBList[p].parentIndex].children[j] = PCBList[PCBList[p].parentIndex].children[j+1];
        }
        PCBList[PCBList[p].parentIndex].numChildren--;
    }
}

int main() {
    initialize();

    // create processes
    create(0);
    create(0);
    create(1);
    create(1);

    // print parent-child relationships
    for (int i = 0; i < MAX_PCB; i++) {
        if (PCBList[i].processID != -1) {
            printf("Process %d (parent %d) has children: ", PCBList[i].processID, PCBList[i].parentIndex);
            for (int j = 0; j < PCBList[i].numChildren; j++) {
                printf("%d ", PCBList[i].children)
            }
        }
    }
}               
