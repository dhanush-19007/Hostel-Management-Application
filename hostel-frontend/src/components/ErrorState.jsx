export default function ErrorState({ message }) {
  return <div className="state-box error"><h3>Something failed</h3><p>{message}</p></div>;
}
