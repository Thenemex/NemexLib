package nemexlib.api.items.data;

public interface IITem {

    void register(IITem item, String name);

    String getName();

    IITem setFull3D(boolean value);
}
