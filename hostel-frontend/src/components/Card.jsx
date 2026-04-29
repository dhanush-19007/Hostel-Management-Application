export default function Card({ title, subtitle, action, children }) {
  return (
    <section className="card">
      {(title || subtitle || action) && (
        <div className="card-head">
          <div>
            {title ? <h3>{title}</h3> : null}
            {subtitle ? <p>{subtitle}</p> : null}
          </div>
          {action}
        </div>
      )}
      {children}
    </section>
  );
}
