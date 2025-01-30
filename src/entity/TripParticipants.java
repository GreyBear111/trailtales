package entity;

import java.util.ArrayList;
import java.util.List;

public class TripParticipants {
    private List<String> participants;

    public TripParticipants() {
        this.participants = new ArrayList<>();
    }

    public void addParticipant(String name) {
        participants.add(name);
    }

    public void removeParticipant(String name) {
        participants.remove(name);
    }

    public List<String> getParticipants() {
        return participants;
    }

    public boolean hasParticipant(String name) {
        return participants.contains(name);
    }

    @Override
    public String toString() {
        return "TripParticipants{" + "participants=" + participants + '}';
    }
}