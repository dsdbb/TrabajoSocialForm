package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

public abstract class PhotoButtonClickedObserver extends BusObserver<PhotoButtonClickedObserver.PhotoButtonClicked> {
    public PhotoButtonClickedObserver(){super(PhotoButtonClicked.class);}

    public static class PhotoButtonClicked{

    }
}
