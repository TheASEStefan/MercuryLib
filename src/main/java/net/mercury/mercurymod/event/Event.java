package net.mercury.mercurymod.event;

public abstract class Event<T>
{
    public abstract void register(T listener);
}
