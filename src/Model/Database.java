package Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private List<SubnetGroup> Sg;

    public Database() {
        Sg = new LinkedList<SubnetGroup>();
    }

    public void addSubnetGroup (SubnetGroup subnetGroup) {
        Sg.add(subnetGroup);
    }

    public void Clear() {
        Sg.clear();
    }

    public void removeSubnetGroup(int index) {
        Sg.remove(index);
    }

    public List<SubnetGroup> getSubnetGroup() {
        return Collections.unmodifiableList(Sg);
    }
}
